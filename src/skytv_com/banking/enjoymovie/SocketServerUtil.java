package skytv_com.banking.enjoymovie;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.json.JSONObject;

import skytv_com.banking.enjoymovie.protocol.CommunicationAction;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SocketServerUtil {
	boolean started = false;
	ServerSocket ss = null;
	List<Client> clients = new ArrayList<Client>();
	private Handler controlHandler;
	
	private static SocketServerUtil instance = null;
	public static SocketServerUtil getInstance(){
		return instance;
	}
	public static void setInstance(SocketServerUtil serverUtil){
		instance = serverUtil;
	}
	
	SocketServerUtil(MovieHomeActivity activity){
		controlHandler = activity.getHandler();
	}
	
	
	public void shutDown(){
		started = false;
		try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public boolean startServer(Context context) {
		try {
			ss = new ServerSocket(8888);
			Log.d("yinhang", "getWifiIp() serverIp"+getWifiIp(context));
			started = true;
		} catch (BindException e) {
			
			Log.d("yinhang", "端口使用中....");
			Log.d("yinhang", "请关掉相关程序并重新运行服务器！");
			return false;
			// System.exit(0);
		} catch (IOException e) {
			Log.d("yinhang", "IOException"+e);
			e.printStackTrace();
			return false;
		}

		try {

			while (started) {
				Socket s = ss.accept();
				Client c = new Client(s);
				Log.d("yinhang", "a client connected!");
				new Thread(c).start();
				clients.add(c);
				// dis.close();
			}
			
		} catch (IOException e) {
			//e.printStackTrace();
			Log.d("yinhang", "connect IO"+e);
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return true;
	}

	public static String getWifiIp(Context context) {
		WifiManager wifimanage=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);//获取WifiManager  
		//检查wifi是否开启  
		if(!wifimanage.isWifiEnabled())  
		{  
		 wifimanage.setWifiEnabled(true);  
		  
		 }  
		WifiInfo wifiinfo= wifimanage.getConnectionInfo();  
		String ip=intToIp(wifiinfo.getIpAddress());  
		return ip;
	}
	private static String intToIp(int i)  
    {  
		
		String result = ( i & 0xFF)+ "." + ((i >> 8 ) & 0xFF) + "." + ((i >> 16 ) & 0xFF) +"."+((i >> 24 ) & 0xFF);  
		return result;
    }  
	public void handMessage(CommunicationAction action){
		final Message message = new Message();
		final int tag = action.getTag();
		final String content = action.getContent();
		final int selected = action.getSelected();
		message.what = tag;//TOAST_TAG
		Bundle b = new Bundle();
		b.putString("key",content);
		b.putInt("selected", selected);
		message.setData(b);
		controlHandler.sendMessage(message);//dispatchMessage
	}
	class Client implements Runnable {
		private Socket s;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private boolean bConnected = false;

		public Client(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				bConnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void send(String str) {
			try {
				dos.writeUTF(str);
			} catch (IOException e) {
				clients.remove(this);
				System.out.println("对方退出了！我从List里面去掉了！");
				// e.printStackTrace();
			}
		}

		public void run() {
			try {
				while (bConnected) {
					String str = dis.readUTF();
					Log.d("yinhang", "Client get String"+str);
					CommunicationAction action = CommunicationAction.jsonToObject(new JSONObject(str));
					//CommunicationAction action = (CommunicationAction) ObjectStreamUtil.stringToObject(str);
					//handMessage(str,HomeActivity.TYPE_CONNECT_TAG);
					//if(action.getTag() == )
					handMessage(action);
					
					Log.d("yinhang", "Client get String2"+str);
					for (int i = 0; i < clients.size(); i++) {
						Client c = clients.get(i);
						c.send(str);
						// System.out.println(" a string send !");
					}
					/*
					 * for(Iterator<Client> it = clients.iterator();
					 * it.hasNext(); ) { Client c = it.next(); c.send(str); }
					 */
					/*
					 * Iterator<Client> it = clients.iterator();
					 * while(it.hasNext()) { Client c = it.next(); c.send(str);
					 * }
					 */
				}
			} catch (EOFException e) {
				System.out.println("Client closed!");
				Log.d("yinhang", "Server Exception"+e);
			} catch (IOException e) {
				e.printStackTrace();
				Log.d("yinhang", "Server Exception2"+e);
			}catch (Exception e) {
				e.printStackTrace();
				Log.d("yinhang", "Server Exception3"+e);
			} 
			finally {
				try {
					if (dis != null)
						dis.close();
					if (dos != null)
						dos.close();
					if (s != null) {
						s.close();
						// s = null;
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		}

	}
}
