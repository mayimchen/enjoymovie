package com.banking.xc.utils.thread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.os.Process;

import com.banking.xc.config.Configuration;

/**
 * 接受线程池管理的线程
 */
public class PooledThread extends Thread {

	private static ThreadPool sFirstPool;// 第一线程池，任务先进入这个池，执行不连网操作的任务，用于避免多个任务同时执行，界面加载慢。
	
	private static ThreadPool sSecondPool;//第二线程池，暂时只存放json网络请求，用于解决图片占用网络,引起带json请求的功能问题。
	
	private static ThreadPool sThirdPool;// 第三线程池，存放图片和文件下载网络请求

	static {
		sFirstPool = new ThreadPool(Integer.parseInt(Configuration.getProperty(Configuration.MAX_FIRST_POOL_SIZE)), Integer.parseInt(Configuration.getProperty(Configuration.INIT_FIRST_POOL_SIZE)));
		sSecondPool = new ThreadPool(Integer.parseInt(Configuration.getProperty(Configuration.MAX_SECOND_POOL_SIZE)), Integer.parseInt(Configuration.getProperty(Configuration.INIT_SECOND_POOL_SIZE)));
		sThirdPool = new ThreadPool(Integer.parseInt(Configuration.getProperty(Configuration.MAX_THIRD_POOL_SIZE)), Integer.parseInt(Configuration.getProperty(Configuration.INIT_THIRD_POOL_SIZE)));
		sThirdPool.init();
		sSecondPool.init();
		sFirstPool.init();// 最后初始化，保证子线程优先级最高。
	}

	public static ThreadPool getFirstThreadPool() {
		return sFirstPool;
	}
	
	public static ThreadPool getSecondThreadPool() {
		return sSecondPool;
	}
	
	public static ThreadPool getThirdThreadPool() {
		return sThirdPool;
	}

    protected List<Runnable> tasks = new ArrayList();// 任务队列
    protected boolean running = false;// 运行标记
    protected boolean stopped = false;// 停止标记
    protected boolean paused = false;// 暂停标记
    protected boolean killed = false;// 结束标记
    private ThreadPool pool;// 所属线程池
   
    public PooledThread(ThreadPool pool){
        this.pool = pool;
    }
    
   /*
    * 添加任务
    * */
    public void putTask(Runnable task){
        tasks.add(task);
    }
   
    /*添加任务队列*/
    public void putTasks(Collection tasks){
        this.tasks.addAll(tasks);
    }
   
    /*移除并返回一个任务*/
    protected Runnable popTask(){
        if(tasks.size() > 0)
            return (Runnable)tasks.remove(0);
        else
            return null;
    }
   
    public boolean isRunning(){
        return running;
    }
   
    /*停止任务*/
    public void stopTasks(){
        stopped = true;
    }
   
    /*以不断短暂睡眠让出资源的方式来实现停止*/
    public void stopTasksSync(){
        stopTasks();
        while(isRunning()){
            try {
                sleep(5);
            } catch (InterruptedException e) {
            }
        }
    }
   
    /*暂停任务*/
    public void pauseTasks(){
        paused = true;
    }
   
    /*以不断短暂睡眠让出资源的方式来实现停止*/
    public void pauseTasksSync(){
        pauseTasks();
        while(isRunning()){
            try {
                sleep(5);
            } catch (InterruptedException e) {
            }
        }
    }
   
    /*结束任务*/
    public void kill(){
        if(!running)// 如果不运行就执行打断
            interrupt();
        else
            killed = true;// 如果运行中就标记停止
    }
   
    /*结束任务*/
    public void killSync(){
        kill();
        while(isAlive()){// 如果是活着就不断地让出资源
            try {
                sleep(5);
            } catch (InterruptedException e) {
            }
        }
    }
   
    /*开始任务*/
    public synchronized void startTasks(){
        running = true;
        this.notify();// 唤醒wait本对象的线程.
    }
   
    public synchronized void run(){
    	Process.setThreadPriority(Process.THREAD_PRIORITY_LOWEST);
        try{
            while(true){// 要么身为空闲线程时,被打断.
                if(!running || tasks.size() == 0){// 符合条件就是空闲线程,执行空闲线程的工作
                    pool.notifyForIdleThread();
                    wait();
                }else{// 繁忙线程
                    Runnable task;
                    while((task = popTask()) != null){
                        task.run();
                        if(stopped){// 每个任务完成后检查停止标记
                            stopped = false;
                            if(tasks.size() > 0){
                                tasks.clear();// 放弃了还没执行的任务
                                break;
                            }
                        }
                        if(paused){// 每个任务完成后检查暂停标记
                            paused = false;
                            if(tasks.size() > 0){
                                break;
                            }
                        }
                    }
                    running = false;
                }

                if(killed){// 每个任务完成后检查结束标记,
                    killed = false;
                    break;// 退出无限循环,就真的结束了.
                }
            }
        }catch(InterruptedException e){
            return;
        }
       
        //System.out.println(Thread.currentThread().getId() + ": Killed");
    }
}