package skytv_com.banking.enjoymovie;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

public class QRImageDialogFragment extends DialogFragment{

	private Handler mHandler;
	Context context;
	private boolean mSetOnce = true;
	private ImageView QRImageView;
	private Bitmap mBitmap;
	
	public QRImageDialogFragment(){
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.qr_dialog_fragment, container);

		QRImageView = (ImageView) view.findViewById(R.id.qr_image);
		QRImageView.setImageBitmap(mBitmap);
		WindowManager.LayoutParams wmlp = getDialog().getWindow()
				.getAttributes();
		wmlp.gravity = Gravity.BOTTOM;
		wmlp.dimAmount = 0.5f;
		wmlp.windowAnimations = R.style.dialog_animation_trans;
		getDialog().getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		
		return view;

	}
	
	
	public void setQRImage(Bitmap bitmap){
		mBitmap = bitmap;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		if (mSetOnce) {
			getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
					WindowManager.LayoutParams.WRAP_CONTENT);
			mSetOnce = false;
		}
	}
	
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_FRAME, R.style.ChannelMoreDlgStyle);
	}
	


}
