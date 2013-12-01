package com.banking.xc.utils;

import android.text.method.NumberKeyListener;

public class InputKeyFilter extends NumberKeyListener {

	/* (non-Javadoc)
	 * @see android.text.method.NumberKeyListener#getAcceptedChars()
	 */
	@Override
	protected char[] getAcceptedChars() {
		// TODO Auto-generated method stub
		char[] myChar={'q','w','e','r','t','y','u','i','o','p','l','k','j','h','g','f','d','s','a','z',
				'x','c','v','b','n','m','1','2','3','4','5','6','7','8','9','0','_','\n'};
		return myChar;
	}

	/* (non-Javadoc)
	 * @see android.text.method.KeyListener#getInputType()
	 */
	@Override
	public int getInputType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
