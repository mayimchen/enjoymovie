package skytv_com.banking.enjoymovie.protocol;

import java.net.ServerSocket;

import skytv_com.banking.enjoymovie.R;
import skytv_com.banking.enjoymovie.SocketServerUtil;
import android.widget.Button;

public class ConnectUtil {
	private static boolean connect = false;
	
	public static void connectClient(Button statusButton){
		connect = true;
		statusButton.setBackgroundResource(R.drawable.connect_button);
	}
	
	public static void connectShutDown(Button statusButton){
		connect = false;
		statusButton.setBackgroundResource(R.drawable.unconnect_button);
	}
	
	public static void connectClose(Button statusButton){
		SocketServerUtil.getInstance().shutDown();
		connectShutDown(statusButton);
	}

	public static boolean isConnect(){
		return connect;
	}
}
