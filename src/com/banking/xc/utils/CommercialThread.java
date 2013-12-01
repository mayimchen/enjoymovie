package com.banking.xc.utils;

/**
 * 活动区 自动切换 线程
 *
 */
public class CommercialThread extends Thread{
	public boolean stop;
	public boolean isRunner;
	public long sleepTime = 5000;
	
	private CommercialThreadListener listner;
	
	public CommercialThread(CommercialThreadListener listner) {
		this.listner = listner;
		stop = false;
		isRunner = true;
	}
	
	@Override
	public void run() {
		synchronized (this) {
			try {
				while (!stop) {
					if (!isRunner) {
						wait();
					}
					listner.doRun();
					Thread.sleep(sleepTime);

				}
			} catch (Exception e) {
				if (Log.D) {
					e.printStackTrace();
				}
			}
		}
	}

	public interface CommercialThreadListener{
		public void doRun();
	}
}

