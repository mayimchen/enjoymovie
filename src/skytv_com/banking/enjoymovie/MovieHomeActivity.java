package skytv_com.banking.enjoymovie;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import skytv_com.banking.enjoymovie.bean.MovieHomeItem;
import skytv_com.banking.enjoymovie.protocol.ConnectUtil;
import skytv_com.banking.enjoymovie.protocol.ProtocolTag;
import skytv_com.banking.enjoymovie.util.MyImageView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;

import com.banking.xc.utils.MyActivity;
import com.banking.xc.utils.MySimpleAdapter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


public class MovieHomeActivity extends MyActivity {
    /** Called when the activity is first created. */
	public static EditText contentET;
	public static Button searchButton;
	private RelativeLayout mLayout1,mLayout2;
	private Button connectButton;
	
	private RelativeLayout layout1,layout2,layout3,layout4,layout5,layout6,
	layout7,layout8,layout9;
	private TextView category1,category2,category3,category4,category5,category6;
	List<MovieHomeItem> mMovieHomeItems;
	
	
	//SocketServerUtil server;
	private String serverIp;
	static QRImageDialogFragment qrImageDialog;
	MyHandler controlHandler;
	
	public static final int TYPE_SHOW_QRFRAGMENT = 1001;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        initView();
        
        controlHandler = new MyHandler();
        adaptLayoutData();
        //serverInit();
        //gridView.performItemClick(view, position, id)
    }
	public void toMovieDetail(String id){
		//WebActivity.startWebActivity(MovieHomeActivity.this,"http://119.167.128.146/youku/677592F2BA23D828A284C15849/0300080200510819EF001303C2D6C8419B9311-4225-5AA7-D174-4CB80B691E68.mp4");
		WebActivity.startWebActivity(MovieHomeActivity.this,"http://112.124.26.176:7777/list#/play/"+id,WebActivity.DETAIL);
		//VideoPlayerActivity.startActivity(MovieHomeActivity.this);
	}
	public void toCategory(String cid){
		WebActivity.startWebActivity(MovieHomeActivity.this,"http://112.124.26.176:7777/list#/tags/"+cid);
		
	}
	
	class MyHandler extends Handler {
		         public MyHandler() {
		        	 
		         }
		
		         public MyHandler(Looper L) {
		             super(L);
		         }
		
		        // 子类必须重写此方法,接受数据
		         @Override
		         public void handleMessage(Message msg) {
		        	Log.d("yinhang", "handleMessage"+msg.what);
		 			switch(msg.what){
		 			case TYPE_SHOW_QRFRAGMENT: //非通讯
		 				showQRCodeByFragment();
		 				break;
		 			case ProtocolTag.TYPE_CONNECT_TAG:
		 				
		 				if(qrImageDialog!=null){
		 					qrImageDialog.dismiss();
		 				}
		 				ConnectUtil.connectClient(connectButton);
		 				Toast.makeText(MovieHomeActivity.this, "已成功连接手机!" , Toast.LENGTH_LONG).show();
		 				break;
		 			case ProtocolTag.TYPE_CLICK_SUBMIT_TAG:
		 				searchButton.performClick();
		 				break;
		 			case ProtocolTag.TYPE_CLICK_CATEGORY_TAG:
		 				final int selectedCategory = msg.getData().getInt("selected");
		 				Toast.makeText(MovieHomeActivity.this, "TYPE_CLICK_CATEGORY_TAG"+selectedCategory , Toast.LENGTH_LONG).show();
		 				switch(selectedCategory){
		 				case ProtocolTag.SELECT_CATEGORY_LOVE:
		 					category1.performClick();
		 					break;
		 				case ProtocolTag.SELECT_CATEGORY_CANDY:
		 					category2.performClick();
		 					break;
		 				case ProtocolTag.SELECT_CATEGORY_ACTION:
		 					category3.performClick();
		 					break;
		 				case ProtocolTag.SELECT_CATEGORY_VENTURE:
		 					category4.performClick();
		 					break;
		 				case ProtocolTag.SELECT_CATEGORY_FICTION:
		 					category5.performClick();
		 					break;
		 				case ProtocolTag.SELECT_CATEGORY_STORY:
		 					category6.performClick();
		 					break;
		 				}
		 				break;
		 			case ProtocolTag.TYPE_TEXT_INPUT_TAG:
		 				final String text = msg.getData().getString("key");
		 				Log.d("yinhang", "TYPE_TEXT_INPUT_TAG"+text);
		 				//searchButton.setBackgroundDrawable(null);
		 				contentET.post(new Runnable(){
		 					@Override
		 					public void run() {
		 						contentET.setText(text,BufferType.EDITABLE);
		 					}
		 					
		 				});
		 				Log.d("yinhang", "TYPE_TEXT_INPUT_TAG finish");
		 				break;
		 			case ProtocolTag.TYPE_VOICE_INPUT_TAG:
		 				//重复了
		 				break;
		 			case ProtocolTag.TYPE_SELECT_MOVIE_TAG:
		 				final int selectedMovie = msg.getData().getInt("selected");
		 				Toast.makeText(MovieHomeActivity.this, "TYPE_SELECT_MOVIE_TAG"+selectedMovie , Toast.LENGTH_LONG).show();
		 				functionMovieMessage(selectedMovie);
		 				break;
		 			}
		 			super.handleMessage(msg);
		 
		         }
	}
	
	public void functionMovieMessage(int selectedMovie){
		
		Log.d("yinhang","functionMovieMessage-->"+selectedMovie);
		switch(selectedMovie){
		case 1:
			 layout1.performClick();
			 break;
		case 2:
			 layout2.performClick();
			 break;
		case 3:
			 layout3.performClick();
			 break;
		case 4:
			 layout4.performClick();
			 break;
		case 5:
			 layout5.performClick();
			 break;
		case 6:
			 layout6.performClick();
			 break;
		case 7:
			 layout7.performClick();
			 break;
		case 8:
			 layout8.performClick();
			 break;
		case 9:
			 mLayout1.performClick();
			 break;
		case 10:
			 mLayout2.performClick();
			 break;
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//
	}
	public void checkConnectState(){
		
	}
	public void showQRCodeByFragment(){
		if(qrImageDialog == null){
			qrImageDialog = new QRImageDialogFragment();
		}
		if(qrImageDialog.isResumed()){
			Log.d("yinhang","serverInit() qrImageDialog.isResumed()->");
			return;
		}
		qrImageDialog.setCancelable(true);
		if(TextUtils.isEmpty(serverIp)){
			Log.d("yinhang","serverInit() serverIp empty->");
			return;
		}
		Bitmap bm = createQRImage(serverIp);
		Log.d("","showQRCodeByFragment() serverIp"+serverIp);
		if(bm!=null){
			qrImageDialog.setQRImage(bm);
		}else{
			return;
		}
		qrImageDialog.show(getSupportFragmentManager(), "prompt");
	}
	
	public Handler getHandler(){
		return controlHandler;
	}
	public Bitmap createQRImage(String ip)
    {
		int QR_WIDTH = 100;
		int QR_HEIGHT = 100;
        try
        {
            //判断URL合法性
            if (ip == null || "".equals(ip) || ip.length() < 1)
            {
                return null;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(ip, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            //下面这里按照二维码的算法，逐个生成二维码的图片，
            //两个for循环是图片横列扫描的结果
            for (int y = 0; y < QR_HEIGHT; y++)
            {
                for (int x = 0; x < QR_WIDTH; x++)
                {
                    if (bitMatrix.get(x, y))
                    {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    }
                    else
                    {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
            }
            //生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
            return bitmap;
        }
        catch (WriterException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public void serverInit(){
    	if(SocketServerUtil.getInstance() == null){
    		Log.d("yinhang","serverInit() server is null");
	    	Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					SocketServerUtil.setInstance(new SocketServerUtil(MovieHomeActivity.this));
				    serverIp = SocketServerUtil.getWifiIp(MovieHomeActivity.this);//server.getServerIp();
				    controlHandler.sendEmptyMessage(TYPE_SHOW_QRFRAGMENT);
				    Log.d("yinhang","serverInit() serverIp"+serverIp);
				    boolean startServer = SocketServerUtil.getInstance().startServer(MovieHomeActivity.this);
				    Log.d("yinhang","Never to here,Error！");
			        //成功的情况
				    if(!startServer){
			           //有错误 TODO
				    	ConnectUtil.connectShutDown(connectButton);
				    }
				}
			});
	    	thread.start();
    	}else{
    		Log.d("yinhang","serverInit() server is not null");
    		Log.d("yinhang","serverInit() server is not null ip:"+serverIp);
    		controlHandler.sendEmptyMessage(TYPE_SHOW_QRFRAGMENT);
    	}
    }
    public void showUnConnectDialoag(){
    		new AlertDialog.Builder(this)
    		.setIcon(R.drawable.icon_logo)
    		.setTitle("中断连接后您的手机将无法控制电视，确定中断吗？")
    		.setPositiveButton(getString(android.R.string.ok),
    				new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog,
    							int which) {
    						ConnectUtil.connectClose(connectButton);
    					}
    				}
    		).setNegativeButton(getString(android.R.string.cancel),
    				new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog,
    							int which) {
    						
    					}
    				}
    		).show();
    }
    
    public void initView(){
    	contentET = (EditText)this.findViewById(R.id.edit_text);
    	searchButton = (Button)this.findViewById(R.id.searchButton);
    	//gridView = (GridView)this.findViewById(R.id.item_gridView);
    	
    	mLayout1 = (RelativeLayout)this.findViewById(R.id.m_layout1);
    	mLayout2 = (RelativeLayout)this.findViewById(R.id.m_layout2);
    	//m1.setImageResource(R.drawable.image_m1);
    	//m2.setImageResource(R.drawable.image_m1);
    	
    	category1 = (TextView) this.findViewById(R.id.id1);
    	category2 = (TextView) this.findViewById(R.id.id2);
    	category3 = (TextView) this.findViewById(R.id.id3);
    	category4 = (TextView) this.findViewById(R.id.id4);
    	category5 = (TextView) this.findViewById(R.id.id5);
    	category6 = (TextView) this.findViewById(R.id.id6);
    	category1.setOnClickListener(categoryOnClickListener);
    	category2.setOnClickListener(categoryOnClickListener);
    	category3.setOnClickListener(categoryOnClickListener);
    	category4.setOnClickListener(categoryOnClickListener);
    	category5.setOnClickListener(categoryOnClickListener);
    	category6.setOnClickListener(categoryOnClickListener);
    	
    	connectButton = (Button)this.findViewById(R.id.connect_button);
    	connectButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.d("yinhang","serverInit() onclick");
				if(ConnectUtil.isConnect()){
					showUnConnectDialoag();
				}else{
					serverInit();
				}
			}
		});
    	
    	searchButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MovieHomeActivity.this,WebActivity.class);//VideoListActivity
				String keyword = contentET.getText().toString();
				Log.d("yinhang", "searchButton"+keyword);
				/*if(TextUtils.isEmpty(keyword)){
					return;
				}else{
					intent.putExtra(VideoListActivity.VIDEO_KEYWORD, keyword);
				}*/
				intent.putExtra("url", "http://112.124.26.176:7777/list");
				MovieHomeActivity.this.startActivity(intent);
			}
		});
    	contentET.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				
			}
    		
    	});
    }
    private OnClickListener categoryOnClickListener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch(id){
			  	case R.id.id1:
			  		toCategory("89");
			  		break;
			  	case R.id.id2:
			  		toCategory("11");
			  		break;
			  	case R.id.id3:
			  		toCategory("80");
			  		break;
			  	case R.id.id4:
			  		toCategory("73");
			  		break;
			  	case R.id.id5:
			  		toCategory("54");
			  		break;
			  	case R.id.id6:
			  		toCategory("40");
			  		break;
			}
		}
    };
    private OnClickListener movieOnClickListener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			String movieId = (String) v.getTag();
			toMovieDetail(movieId);
		}
    };
    
    public void adaptLayoutData(){
    	
    	mMovieHomeItems = new ArrayList<MovieHomeItem>();
    	for(int i=0;i<11;i++){
    		MovieHomeItem item = new MovieHomeItem();
    		item.setDesc("测试影评");
    		item.setMovieId("82011");
    		mMovieHomeItems.add(item);
    	}
    	MovieHomeItem item1 = new MovieHomeItem();
		item1.setDesc("测试影评");
		item1.setMovieId("82011");
		mMovieHomeItems.add(item1);
		MovieHomeItem item2 = new MovieHomeItem();
		item2.setDesc("测试影评");
		item2.setMovieId("82011");
		mMovieHomeItems.add(item2);
    	
    	layout1 = (RelativeLayout) this.findViewById(R.id.item_layout1).findViewById(R.id.movie_item1);
    	layout2 = (RelativeLayout) this.findViewById(R.id.item_layout1).findViewById(R.id.movie_item2);
    	layout3 = (RelativeLayout) this.findViewById(R.id.item_layout1).findViewById(R.id.movie_item3);
    	layout4 = (RelativeLayout) this.findViewById(R.id.item_layout2).findViewById(R.id.movie_item1);
    	layout5 = (RelativeLayout) this.findViewById(R.id.item_layout2).findViewById(R.id.movie_item2);
    	layout6 = (RelativeLayout) this.findViewById(R.id.item_layout2).findViewById(R.id.movie_item3);
    	layout7 = (RelativeLayout) this.findViewById(R.id.item_layout3).findViewById(R.id.movie_item1);
    	layout8 = (RelativeLayout) this.findViewById(R.id.item_layout3).findViewById(R.id.movie_item2);
    	layout9 = (RelativeLayout) this.findViewById(R.id.item_layout3).findViewById(R.id.movie_item3);
    	layout1.setOnClickListener(movieOnClickListener);
    	layout2.setOnClickListener(movieOnClickListener);
    	layout3.setOnClickListener(movieOnClickListener);
    	layout4.setOnClickListener(movieOnClickListener);
    	layout5.setOnClickListener(movieOnClickListener);
    	layout6.setOnClickListener(movieOnClickListener);
    	layout7.setOnClickListener(movieOnClickListener);
    	layout8.setOnClickListener(movieOnClickListener);
    	layout9.setOnClickListener(movieOnClickListener);
    	mLayout1.setOnClickListener(movieOnClickListener);
    	mLayout2.setOnClickListener(movieOnClickListener);
    	
    	adapterItemToLayout(layout1,mMovieHomeItems.get(0));
    	adapterItemToLayout(layout2,mMovieHomeItems.get(1));
    	adapterItemToLayout(layout3,mMovieHomeItems.get(2));
    	adapterItemToLayout(layout4,mMovieHomeItems.get(3));
    	adapterItemToLayout(layout5,mMovieHomeItems.get(4));
    	adapterItemToLayout(layout6,mMovieHomeItems.get(5));
    	adapterItemToLayout(layout7,mMovieHomeItems.get(6));
    	adapterItemToLayout(layout8,mMovieHomeItems.get(7));
    	adapterItemToLayout(layout9,mMovieHomeItems.get(8));
    	
    	
    }
    public void adapterItemToLayout(RelativeLayout layout,MovieHomeItem item){
    	ImageView iv = (ImageView) layout.findViewById(R.id.movie_pic);
    	layout.setTag(item.getMovieId());
    	if(item.getPicResId()!=0){
    		iv.setImageResource(item.getPicResId());
    	}
    	TextView tv = (TextView) layout.findViewById(R.id.movie_desc);
    	tv.setText(item.getDesc());
    }
}