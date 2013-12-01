package com.banking.xc.constant;
public class NetworkConstant {

    public static final class NetworkState{
        /**上网方式:cmnet 或 cmwap*/
        public final static int ALL = 0;
        /**上网方式:cmnet*/
        public final static int CMNET = 1;
        /**上网方式:cmwap*/
        public final static int CMWAP = 2;
        /**上网方式:cmlan*/
        public final static int CMLAN = 3;
    }
    
    public static final class APN{
        /*-------APN切换过程中的网络状态标识--------*/
        /**APN切换前初始化*/
        public final static int INIT = 9005;
        /**段开网络连接*/
        public final static int DISCONNECTED = 9006;
        /**网络正在连接中*/
        public final static int CONNECTING = 9007;
        /**网络连接成功*/
        public final static int CONNECTED = 9008;
        /**检测网络是否可用*/
        public static final int CHECK_NETWORK = 9009;
        
        /*-------APN切换之后的结果--------*/
        /**APN切换成功*/
        public final static int SUCCESS = 9000;
        /**APN切换失败*/
        public final static int FAIL = 9001;
        /**APN切换超时*/
        public final static int TIMEOUT = 9002; 
    }
    
    public static final class TaskState{
        public final static int SUCCESS = 1000;
    }
}
