package skytv_com.banking.enjoymovie.protocol;

public class ProtocolTag {
	
	//public static final int TYPE_SHOW_QRFRAGMENT = 10;//弹出二维码dialog
	public static final int TYPE_CONNECT_TAG = 0;//成功连接,隐藏dialog,弹出toast提示
	public static final int TYPE_UNCONNECT_TAG = 6;
	public static final int TYPE_CLICK_SUBMIT_TAG = 1;//提交了输入
	public static final int TYPE_CLICK_CATEGORY_TAG = 2; //特指点击了某个分类
	public static final int TYPE_TEXT_INPUT_TAG = 3;//输入了哪些内容，所有内容哦~
	public static final int TYPE_VOICE_INPUT_TAG = 4;//语音输入
	public static final int TYPE_SELECT_MOVIE_TAG = 5;//点击了
	//其他页面的列表输入的Tag
	
	
	public static final int SELECT_CATEGORY_LOVE = 11;
	public static final int SELECT_CATEGORY_CANDY = 12;
	public static final int SELECT_CATEGORY_ACTION = 13;
	public static final int SELECT_CATEGORY_VENTURE = 14;
	public static final int SELECT_CATEGORY_FICTION = 15;
	public static final int SELECT_CATEGORY_STORY = 16;
	
	//public static final int SELECT_MOVIE_TAG = 100+index;
	
	
}
