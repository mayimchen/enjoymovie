package com.banking.xc.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
	
	private String SDPATH;  
    
    private final int FILESIZE = 1 * 1024;   
      
    public String getSDPATH(){  
        return SDPATH;  
    }  
      
    public FileUtils(){  
        //得到当前外部存储设备的目录( /SDCARD )  
//        SDPATH = Environment.getExternalStorageDirectory() + "/";  
    }  
      
    /**  
     * 创建文件  
     * @param fileName  
     * @return  
     * @throws IOException  
     */  
    public File createFile(String fileName) throws IOException{  
        File file = new File(fileName);  
        file.createNewFile();  
        return file;  
    }  
      
    /**  
     * 在SD卡上创建目录  
     * @param dirName  
     * @return  
     */  
    public File createSDDir(String dirName){  
        File dir = new File(SDPATH + dirName);  
        dir.mkdir();  
        return dir;  
    }  
      
    /**  
     * 判断SD卡上的文件夹是否存在  
     * @param fileName  
     * @return  
     */  
    public boolean isFileExist(String fileName){  
        File file = new File(SDPATH + fileName);  
        return file.exists();  
    }  
      
    /**  
     * 判断SD卡上的文件夹是否存在  
     * @param fileName  
     * @return  
     */  
    public void deleFile(String fileName){  
        File file = new File(SDPATH + fileName);  
        if(file.exists()){
        	file.deleteOnExit();
        }
        return ;  
    }
    /**  
     * 将一个InputStream里面的数据写入到SD卡中  
     * @param path  
     * @param fileName  
     * @param input  
     * @return  
     */  
    public File writeFromInput(String path,String fileName,InputStream input){  
        File file = null;  
        FileOutputStream output = null;  
        try {  
//            createSDDir(path);  
        	file = new File(path+fileName);
        	if(file.exists()){
        		file.deleteOnExit();
        	}
        	file.createNewFile();  
        	
            output = new FileOutputStream(file);  
            byte[] buffer = new byte[FILESIZE];  
            int readLength = 0;
            BufferedInputStream bufferedInputStream = new BufferedInputStream(input);
            while((readLength = bufferedInputStream.read(buffer)) != -1){  
                output.write(buffer,0,readLength);
            }  
            output.flush();  
        }   
        catch (Exception e) {  
            e.printStackTrace();  
        }  
        finally{  
            try {  
            	if(output!=null){
            		output.close();
            	}
                  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  

        return file;  
    }
    

}
