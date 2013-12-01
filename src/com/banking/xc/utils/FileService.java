package com.banking.xc.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import skytv_com.banking.enjoymovie.MyApplication;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import com.banking.xc.utils.HttpGroup.StopController;

public class FileService {

	private static final String FILE_MODE_WORLD_WRITEABLE = "622";// 外部可写-rw--w--w-

	private static final String FILE_MODE_WORLD_READABLE = "644";// 外部可读-rw-r--r--

	private static final String FILE_MODE_WORLD_ACCESS = "755";// 外部可执行(访问)drwxr-xr-x

	private static final String aplcationDir = "/jingdong";// 定义本应用在SD卡上所使用的文件夹

	private static final String SHARE_IMAGE_NAME = "shareimage.jpg";// 供外部应用分享的图片名称

	private static final String SYSTEM_OPERATOR = "/";

	private static final String TAG = "FileService";

	/**
	 * 最大的图片缓存存储数量
	 */
	private static final int MAX_IMAGE_SAVE_NUM = 4096;

	/**
	 * 图片缓存数量未知 
	 */
	private static final int UN_KNOW = -20;

	/**
	 * sd卡中的缓存图片数量
	 */
	private static int sdCardImageCacheNum = UN_KNOW;

	/**
	 * 手机内存中的图片缓存数量
	 */
	private static int phoneMemoryImageCacheNum = UN_KNOW;

	/**
	 * 后台清理多余图片的线程
	 */
	private static DeleteOverImageThread deleteOverImageThread;

	// 目录类型
	public static final int IMAGE_DIR = 1;// 图片缓存目录
	public static final int JSON_DIR = 2;//
	public static final int CAMERA_DIR = 3;// 摄像头临时图片目录
	public static final int PERSIST_DIR = 4;//持久化文件目录

	// 对应目录
	private static Directory imageDir;
	private static Directory jsonDir;
	private static int imageDirState;// -1:没有适合存储的空间,0:未定,1:内部存储空间,2:外部存储空间
	private static int jsonDirState;// -1:没有适合存储的空间,0:未定,1:内部存储空间,2:外部存储空间
	public static boolean isNeedReSetupStorageStateJson = true;// 是否需要重新设置存储标志位,默认为true，应用新启动需要检查
	public static boolean isNeedReSetupStorageStateImage = true;// 是否需要重新设置存储标志位,默认为true，应用新启动需要检查

	private static final String IMAGE_CHILD_DIR = "/image";// json 子目录
	private static final String JSON_CHILD_DIR = "/json";// image 子目录
	private static final String CAMERA_CHILD_DIR = "/camera";// camera 子目录
	private static final String PERSIST_CHILD_DIR = "/persist";//persist目录

	public static final String CACHE_EXT_NAME_IMAGE = ".image";
	public static final String CACHE_EXT_NAME_JSON = ".json";

	private static final long THRESHOLD_JSON_SIZE = 1024 * 1024 * 1;// json需要的空间大小1M
	private static final long THRESHOLD_IMAGE_SIZE = 1024 * 1024 * 8;// 图片需要的空间大小8M
	private static final long CAMERA_SIZE_THRESHOLD = 1024 * 1024 * 16;// 判断摄像头临时存储空间 16M
	private static final long PERSIST_FILE_SIZE = 1024 * 1024 * 8;//持久化图片，暂定8M空间
	private static final long THRESHOLD_IMAGE_CLEAR_SIZE = 1024 * 1024 * 50;// 存储空间小于50M时，需要清除缓存图片

	// 内置存储空间的目录类型
	public static final int INTERNAL_TYPE_FILE = 1;
	public static final int INTERNAL_TYPE_CACHE = 2;

	/**
	 * @author lijingzuo
	 * 
	 *         Time: 2010-12-31 上午10:37:19
	 * 
	 *         Name:
	 * 
	 *         Description: SDCard正确安装，并且可读写
	 * 
	 * @return
	 * 
	 */
	public static boolean isReady() {
		return externalMemoryAvailable();
	}

	public static boolean externalMemoryAvailable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取文件输出流 TODO
	 */
	public static FileOutputStream openFileOutput(FileGuider fileGuider) throws FileNotFoundException {

		long availableSize = fileGuider.getAvailableSize();
		if (0 != availableSize) {
			if (Directory.INTERNAL == fileGuider.getSpace() && // 内部存储空间不足
					getAvailableInternalMemorySize() < availableSize) {
				return null;
			} else if (Directory.EXTERNAL == fileGuider.getSpace() && // 外部存储空间不足
					getAvailableExternalMemorySize() < availableSize) {
				return null;
			}
		}

		return MyApplication.getInstance().openFileOutput(fileGuider.getFileName(), fileGuider.getMode());

	}

	/**
	 *	获取文件全路径
	 * @param fileGuider
	 * @return
	 */
	public static String getFileAbsolutePath(FileGuider fileGuider) {
		return MyApplication.getInstance().getFilesDir().getAbsolutePath() + SYSTEM_OPERATOR + fileGuider.getFileName();
	}

	/**
	 * @author lijingzuo
	 * 
	 *         Time: 2010-12-31 上午10:27:03
	 * 
	 *         Name:
	 * 
	 *         Description: 获得 External 应用根目录
	 * 
	 * @return
	 * 
	 */
	public static File getExternalDirectory(String childDirName) {
		if (Log.D) {
			Log.d(TAG, "getExternalDirectory() -->> ");
		}
		File dir = new File(Environment.getExternalStorageDirectory(), aplcationDir + ((null != childDirName) ? childDirName : ""));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	/**
	 * @author lijingzuo
	 * 
	 *         Time: 2010-12-31 上午10:27:03
	 * 
	 *         Name:
	 * 
	 *         Description: 获得 Internal 应用根目录
	 * 
	 * @return
	 * 
	 */
	public static File getInternalDirectory(String childDirName, int internalType, boolean isWorldAccess) {

		if (Log.D) {
			Log.d(TAG, "getInternalDirectory() -->> ");
		}

		File typeDir = null;

		switch (internalType) {

		case INTERNAL_TYPE_FILE:
			typeDir = MyApplication.getInstance().getFilesDir();
			break;

		case INTERNAL_TYPE_CACHE:
			typeDir = MyApplication.getInstance().getCacheDir();
			break;

		}

		File dir = new File(typeDir, aplcationDir + ((null != childDirName) ? childDirName : ""));
		if (!dir.exists()) {
			dir.mkdirs();
		}

		if (isWorldAccess) {// 外部可访问
			chModFile(FILE_MODE_WORLD_ACCESS, new File(typeDir, aplcationDir));// /jingdong文件夹外部可访问
			chModFile(FILE_MODE_WORLD_ACCESS, dir);// 图片文件夹外部可访问
		}

		if (Log.D) {
			Log.d(TAG, "getInternalDirectory() dir.getAbsolutePath() -->> " + dir.getAbsolutePath());
		}
		if (Log.D) {
			Log.d(TAG, "getInternalDirectory() dir.exists() -->> " + dir.exists());
		}

		return dir;
	}

	/**
	 * @author lijingzuo
	 * 
	 *         Time: 2010-12-31 上午10:27:03
	 * 
	 *         Name:
	 * 
	 *         Description: 获得 Internal 应用根目录（缓存）
	 * 
	 * @return
	 * 
	 */
	public static File getInternalDirectory(String childDirName, boolean isWorldAccess) {
		return getInternalDirectory(childDirName, INTERNAL_TYPE_CACHE, isWorldAccess);
	}

	/**
	 * 选用json所需要存储空间并建立所需子目录
	 */
	private static Directory getDirectoryByJsonSize(String childDirName) {
		if (Log.D) {
			Log.d(TAG, "getDirectoryByJsonSize() -->> ");
		}
		if (getAvailableInternalMemorySize() > THRESHOLD_JSON_SIZE) {// 选用 Internal
			// 空间
			if (Log.D) {
				Log.d(TAG, "getDirectoryByJsonSize() -->> INTERNAL");
			}
			return new Directory(getInternalDirectory(childDirName, false), Directory.INTERNAL);
		} else if (getAvailableExternalMemorySize() > THRESHOLD_JSON_SIZE) {// 选用
			// External
			// 空间
			if (Log.D) {
				Log.d(TAG, "getDirectoryByJsonSize() -->> EXTERNAL");
			}
			return new Directory(getExternalDirectory(childDirName), Directory.EXTERNAL);
		}
		return null;
	}

	/**
	 * 选用image所需要存储空间并建立所需子目录,规则：1.检查有SD卡就用SD卡；2内部存储大于8M用内部存储
	 */
	private static Directory getDirectoryByImageSize(String childDirName) {
		if (Log.D) {
			Log.d(TAG, "getDirectoryByImageSize() -->> ");
		}
		if (externalMemoryAvailable()) { // 选用扩展卡
			if (Log.D) {
				Log.d(TAG, "getDirectoryByImageSize() -->> EXTERNAL");
			}
			return new Directory(getExternalDirectory(childDirName), Directory.EXTERNAL);
		} else if (getAvailableInternalMemorySize() > THRESHOLD_IMAGE_SIZE) {// 选用 Internal
			if (Log.D) {
				Log.d(TAG, "getDirectoryByImageSize() -->> INTERNAL");
			}
			return new Directory(getInternalDirectory(childDirName, false), Directory.INTERNAL);
		}
		return null;
	}

	private static Directory getDirectoryByPersistFileSize(String childDirName) {
		if (getAvailableInternalMemorySize() > PERSIST_FILE_SIZE) {// 选用 Internal
			// 空间
			if (Log.D) {
				Log.d(TAG, "getDirectoryByJsonSize() -->> INTERNAL");
			}
			return new Directory(getInternalDirectory(childDirName, false), Directory.INTERNAL);
		} else { //没有内部返回空
			return null;
		}
	}

	/**
	 * 尝试获得所需路径
	 */

	public static Directory getDirectory(int dir) {

		switch (dir) {
		case JSON_DIR:
			return getJsonDirectory();
		case IMAGE_DIR:
			return getImageDirectory();
		case CAMERA_DIR:
			return getCameraDirectory();
		case PERSIST_DIR:
			return getPersistDirectory();
		default:
			return null;
		}

	}

	/**
	 * 尝试获得 json 存储路径
	 */
	private static Directory getJsonDirectory() {

		if (Log.D) {
			Log.d(TAG, "getJsonDirectory() jsonDirState -->> " + jsonDirState);
		}
		if (Log.D) {
			Log.d(TAG, "getJsonDirectory() jsonDir -->> " + jsonDir);
		}

		if (!isNeedReSetupStorageStateJson && jsonDirState == -1) {
			return null;
		} else if (!isNeedReSetupStorageStateJson && null != jsonDir) {
			return jsonDir;
		}
		if (isNeedReSetupStorageStateJson) {

			if (Log.D) {
				Log.d(TAG, "getJsonDirectory() no preferences -->> ");
			}

			Directory directory = getDirectoryByJsonSize(JSON_CHILD_DIR);
			if (null == directory) {// 没有可用存储空间
				if (Log.D) {
					Log.d(TAG, "getJsonDirectory() no free size -->> ");
				}
				jsonDirState = -1;
				// 不记录（应用设置），因为希望将来能再次检查
				return null;
			} else {// 存在存储空间
				if (Log.D) {
					Log.d(TAG, "getJsonDirectory() has free size -->> ");
				}
				jsonDir = directory;
				jsonDirState = directory.getSpace();
				isNeedReSetupStorageStateJson = false;// 此处将标志位改回
				return jsonDir;
			}

		} else {

			if (Log.D) {
				Log.d(TAG, "getJsonDirectory() is preferences -->> ");
			}

			if (jsonDirState == 2) {// 外部存储空间
				if (!externalMemoryAvailable()) {// 预防外部存储空间意外移除
					if (Log.D) {
						Log.d(TAG, "getJsonDirectory() no external -->> ");
					}
					jsonDirState = -1;
					return null;
				}
			}

			if (Log.D) {
				Log.d(TAG, "getJsonDirectory() jsonFileCachePath -->> " + jsonDir.getDir().getAbsolutePath());
			}

			jsonDir = new Directory(new File(jsonDir.getDir().getAbsolutePath()), jsonDirState == 1 ? Directory.INTERNAL : Directory.EXTERNAL);
			File dir = jsonDir.getDir();
			if (!dir.exists()) {// 预防创建之后被意外删除
				dir.mkdirs();
			}
			return jsonDir;
		}
	}

	/**
	 * 尝试获得 image 存储路径
	 */
	private static Directory getImageDirectory() {

		if (Log.D) {
			Log.d(TAG, "getImageDirectory() imageDirState -->> " + imageDirState);
		}
		if (Log.D) {
			Log.d(TAG, "getImageDirectory() imageDir -->> " + imageDir);
		}

		if (!isNeedReSetupStorageStateImage && imageDirState == -1) {
			return null;
		} else if (!isNeedReSetupStorageStateImage && null != imageDir) {
			return imageDir;
		}

		if (isNeedReSetupStorageStateImage) {

			Directory directory = getDirectoryByImageSize(IMAGE_CHILD_DIR);
			if (null == directory) {// 没有可用存储空间
				imageDirState = -1;
				// 不记录（应用设置），因为希望将来能再次检查
				return null;
			} else {// 存在存储空间
				if (Log.D) {
					Log.d(TAG, "getImageDirectory() has free size -->> ");
				}
				imageDir = directory;
				imageDirState = directory.getSpace();

				isNeedReSetupStorageStateImage = false;// 此处将标志位改回
				return imageDir;
			}

		} else {

			if (imageDirState == 2) {// 外部存储空间
				if (!externalMemoryAvailable()) {// 预防外部存储空间意外移除
					imageDirState = -1;
					return null;
				}
			}

			if (Log.D) {
				Log.d(TAG, "getImageDirectory() imageFileCachePath -->> " + imageDir.getDir().getAbsolutePath());
			}

			imageDir = new Directory(new File(imageDir.getDir().getAbsolutePath()), imageDirState == 1 ? Directory.INTERNAL : Directory.EXTERNAL);
			File dir = imageDir.getDir();
			if (!dir.exists()) {// 预防创建之后被意外删除
				dir.mkdirs();
			}
			return imageDir;
		}
	}

	/**
	 * 获取摄像头临时图片存储路径，规则： 1、只存储SD卡中。 3、没有存储返回为null
	 * 
	 * @return
	 */
	private static Directory getCameraDirectory() {
		if (getTotalExternalMemorySize() > CAMERA_SIZE_THRESHOLD) {// 优先选用外部SD卡
			return new Directory(getExternalDirectory(CAMERA_CHILD_DIR), Directory.EXTERNAL);
		}
		return null;
	}

	/**
	 * 获取持久化文件存储路径，该路径下文件不会被清除。内部有控件存在内部，内部没空间放弃存储
	 * @return
	 */
	private static Directory getPersistDirectory() {

		Directory directory = getDirectoryByPersistFileSize(PERSIST_CHILD_DIR);
		return directory;

	}

	/**
	 * 保存内容到SDCard
	 * 
	 * @param filename
	 *            文件名称
	 * @param content
	 *            文件内容
	 * @throws Exception
	 */
	public void saveToSDCard(String filename, String content) throws Exception {
		saveToSDCard(null, filename, content);
	}

	/**
	 * 保存文件
	 */
	public static boolean saveToSDCard(Directory directory, String fileName, String content) {
		return saveToSDCard(directory, fileName, content, Context.MODE_PRIVATE);
	}

	/**
	 * 保存文件
	 */
	public static boolean saveToSDCard(Directory directory, String fileName, String content, int mode) {
		if (null == content) {
			return false;
		}
		return saveToSDCard(directory, fileName, content.getBytes(), mode);
	}

	/**
	 * 保存文件
	 */
	public static boolean saveToSDCard(Directory directory, String fileName, byte[] data) {
		if (null == data) {
			return false;
		}
		return saveToSDCard(directory, fileName, data, Context.MODE_PRIVATE);
	}

	/**
	 * 获取一个文件下有多少个文件 
	 * @param dir
	 * @return
	 */
	private static int getSubFilesNum(File dir) {
		if (dir != null && dir.exists()) {
			String[] names = dir.list();
			if (names != null) {
				return names.length;
			}
		}
		return UN_KNOW;
	}

	/**
	 * 尝试清除片文件夹
	 * @param directory 
	 */
	private static synchronized void tryCleanImageDir(Directory directory) {

		File imageDir = directory.getDir();

		int currentNum = 0;

		switch (directory.space) {
		case Directory.INTERNAL: // 如果从来没有加载过手机内存中的图片数量，那么开始加载手机内存中的图片数量
			if (phoneMemoryImageCacheNum == UN_KNOW) {
				phoneMemoryImageCacheNum = getSubFilesNum(imageDir);
			}
			currentNum = phoneMemoryImageCacheNum;
			break;

		case Directory.EXTERNAL: // 如果从来没有加载过sd卡中的图片数量，那么开始加载sd卡中的图片数量
			if (sdCardImageCacheNum == UN_KNOW) {
				sdCardImageCacheNum = getSubFilesNum(imageDir);
			}
			currentNum = sdCardImageCacheNum;
			break;
		}

		if (Log.D) {
			Log.d(TAG, "tryCleanImageDir currentNum:" + currentNum);
		}

		if (currentNum > MAX_IMAGE_SAVE_NUM && deleteOverImageThread == null) {
			deleteOverImageThread = new DeleteOverImageThread(imageDir);
			deleteOverImageThread.start();
		}
	}

	/**
	 * 将缓存图片中数量清楚到 最大数量的一半
	 * @param imageDir2
	 */
	private static void deleteOverImageWithDir(File imageDir) {

		if (imageDir == null || !imageDir.exists()) {
			return;
		}

		File[] files = imageDir.listFiles();

		if (files == null) {
			return;
		}

		int length = files.length;

		if (length < 1) {
			return;
		}

		Arrays.sort(files, new FileSorter());

		int needDeleteNum = length - MAX_IMAGE_SAVE_NUM / 2;

		if (Log.D) {
			Log.d(TAG, "deleteOverImageWithDir needDeleteNum:" + needDeleteNum);
		}

		if (needDeleteNum < 0) {
			return;
		}

		File temp;
		for (int i = 0; i < needDeleteNum && i <= length; i++) {

			temp = files[i];

			if (temp != null && temp.exists()) {
				if (Log.D) {
					Log.d(TAG, "deleteOverImageWithDir temp:" + temp);
				}
				temp.delete();
			}
		}

	}

	/**
	 * 根据类型保存文件 
	 * @param directory
	 * @param fileName
	 * @param content
	 * @param type
	 * @return
	 */
	public static boolean saveToSDCardWithType(Directory directory, String fileName, String content, int type) {
		if (TextUtils.isEmpty(content)) {
			return false;
		}
		return saveToSDCardWithType(directory, fileName, content.getBytes(), type);
	}

	/**
	 * 根据类型保存文件
	 * @param directory
	 * @param fileName
	 * @param data
	 * @param type
	 * @return
	 */
	public static boolean saveToSDCardWithType(Directory directory, String fileName, byte[] data, int type) {
		if (null == data || data.length < 1 || TextUtils.isEmpty(fileName) || directory == null) {
			return false;
		}

		if (Log.D) {
			Log.d(TAG, "saveToSDCardWithType phoneMemoryImageCacheNum:" + phoneMemoryImageCacheNum);
			Log.d(TAG, "saveToSDCardWithType sdCardImageCacheNum:" + sdCardImageCacheNum);
		}

		switch (type) {
		case IMAGE_DIR: // 图片目录
			tryCleanImageDir(directory);
			break;

		case JSON_DIR: // json目录
			break;
		}

		boolean result = saveToSDCard(directory, fileName, data);

		if (Log.D) {
			Log.d(TAG, "saveToSDCardWithType result:" + result);
		}

		if (result && type - IMAGE_DIR == 0) {
			incrementImageNum(directory);
		}

		return result;

	}

	/**
	 * 图片计数器增长
	 * @param directory 
	 */
	private static synchronized void incrementImageNum(Directory directory) {

		if (directory == null) {
			return;
		}

		File dir = directory.getDir();

		if (dir == null || !dir.exists()) {
			return;
		}

		switch (directory.space) {
		case Directory.INTERNAL: // 如果从来没有加载过手机内存中的图片数量，那么开始加载手机内存中的图片数量
			if (phoneMemoryImageCacheNum == UN_KNOW) {
				phoneMemoryImageCacheNum = getSubFilesNum(dir);
			}
			phoneMemoryImageCacheNum++;
			break;

		case Directory.EXTERNAL: // 如果从来没有加载过sd卡中的图片数量，那么开始加载sd卡中的图片数量
			if (sdCardImageCacheNum == UN_KNOW) {
				sdCardImageCacheNum = getSubFilesNum(dir);
			}
			sdCardImageCacheNum++;
			break;
		}

	}

	/**
	 * 重置SD卡图片标志位的数量
	 * @param directory 
	 */
	private static synchronized void resetSdcardImageNum(int resetNum) {
		sdCardImageCacheNum = resetNum;
	}

	/**
	 * 重置手机存储图片标志位的数量
	 * @param directory 
	 */
	private static synchronized void resetPhoneMemoryImageNum(int resetNum) {
		phoneMemoryImageCacheNum = resetNum;
	}

	/**
	 * 保存文件
	 */

	public static boolean saveToSDCard(Directory directory, String fileName, byte[] data, int mode) {

		if (directory == null || TextUtils.isEmpty(fileName) || data == null) {
			return false;
		}

		File dir = directory.getDir();

		File file = new File(dir, fileName);
		FileOutputStream outStream = null;

		// 更新文件的读写权限
		if (mode == Context.MODE_WORLD_READABLE) {
			chModFile(FILE_MODE_WORLD_READABLE, file);// 图片文件外部可读-rw----r--
		} else if (mode == Context.MODE_WORLD_WRITEABLE) {
			chModFile(FILE_MODE_WORLD_WRITEABLE, file);// 图片文件外部可写-rw-----w-
		}// 否则为默认

		try {
			outStream = new FileOutputStream(file);
			if (null != outStream) {
				outStream.write(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (null != outStream) {
				try {
					outStream.close();
				} catch (IOException e) {
				}
			}
		}
		return true;

	}

	/**
	 * 保存内容
	 * 
	 * @param filename
	 *            文件名称
	 * @param content
	 *            文件内容
	 * @throws Exception
	 */
	public void save(String filename, String content) throws Exception {
		FileOutputStream outStream = MyApplication.getInstance().openFileOutput(filename, Context.MODE_PRIVATE);
		outStream.write(content.getBytes());
		outStream.close();
	}

	/**
	 * 以追加方式保存内容
	 * 
	 * @param filename
	 *            文件名称
	 * @param content
	 *            文件内容
	 * @throws Exception
	 */
	public void saveAppend(String filename, String content) throws Exception {// ctrl+shift+y变小写/x变大写
		FileOutputStream outStream = MyApplication.getInstance().openFileOutput(filename, Context.MODE_APPEND);
		outStream.write(content.getBytes());
		outStream.close();
	}

	/**
	 * 保存内容,允许其他应用对其进行读访问
	 * 
	 * @param filename
	 *            文件名称
	 * @param content
	 *            文件内容
	 * @throws Exception
	 */
	public void saveReadable(String filename, String content) throws Exception {// ctrl+shift+y变小写/x变大写
		FileOutputStream outStream = MyApplication.getInstance().openFileOutput(filename, Context.MODE_WORLD_READABLE);
		outStream.write(content.getBytes());
		outStream.close();
	}

	/**
	 * 保存内容,允许其他应用对其进行写访问
	 * 
	 * @param filename
	 *            文件名称
	 * @param content
	 *            文件内容
	 * @throws Exception
	 */
	public void saveWriteable(String filename, String content) throws Exception {// ctrl+shift+y变小写/x变大写
		FileOutputStream outStream = MyApplication.getInstance().openFileOutput(filename, Context.MODE_WORLD_WRITEABLE);
		outStream.write(content.getBytes());
		outStream.close();
	}

	/**
	 * 保存内容,允许其他应用对其进行读写访问
	 * 
	 * @param filename
	 *            文件名称
	 * @param content
	 *            文件内容
	 * @throws Exception
	 */
	public void saveReadableWriteable(String filename, String content) throws Exception {// ctrl+shift+y变小写/x变大写
		FileOutputStream outStream = MyApplication.getInstance().openFileOutput(filename, Context.MODE_APPEND + Context.MODE_WORLD_WRITEABLE + Context.MODE_WORLD_READABLE);
		outStream.write(content.getBytes());
		outStream.close();
	}

	/**
	 * 读取内容
	 * 
	 * @param filename
	 *            文件名称
	 * @return 文件内容
	 * @throws Exception
	 */
	public String read(String filename) throws Exception {
		FileInputStream inStream = MyApplication.getInstance().openFileInput(filename);
		byte[] data = readInputStream(inStream);
		return new String(data);
	}

	/**
	 * 读取内容
	 * 
	 * @param filename
	 *            文件名称
	 * @return 文件内容
	 * @throws Exception
	 */
	public byte[] readAsByteArray(String filename) throws Exception {
		FileInputStream inStream = MyApplication.getInstance().openFileInput(filename);
		return readInputStream(inStream);
	}

	// 手机：自带存储空间，外部插进来SDCard

	public byte[] readInputStream(FileInputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		outStream.close();
		return outStream.toByteArray();
	}

	private static final int ERROR = -1;

	/**
	 * @author lijingzuo
	 * 
	 *         Time: 2011-3-23 下午04:23:09
	 * 
	 *         Name:
	 * 
	 *         Description: 内部可用空间大小
	 * 
	 * @return
	 * 
	 */
	static public long getAvailableInternalMemorySize() {

		File path = Environment.getDataDirectory();

		StatFs stat = new StatFs(path.getPath());

		long blockSize = stat.getBlockSize();

		long availableBlocks = stat.getAvailableBlocks();

		return availableBlocks * blockSize;

	}

	/**
	 * @author lijingzuo
	 * 
	 *         Time: 2011-3-23 下午04:22:22
	 * 
	 *         Name:
	 * 
	 *         Description: 内部总空间大小
	 * 
	 * @return
	 * 
	 */
	static public long getTotalInternalMemorySize() {

		File path = Environment.getDataDirectory();

		StatFs stat = new StatFs(path.getPath());

		long blockSize = stat.getBlockSize();

		long totalBlocks = stat.getBlockCount();

		long result = totalBlocks * blockSize;

		if (Log.D) {
			Log.d(TAG, "getTotalInternalMemorySize() -->> " + result);
		}

		return result;

	}

	/**
	 * @author lijingzuo
	 * 
	 *         Time: 2011-3-23 下午04:23:48
	 * 
	 *         Name:
	 * 
	 *         Description: 外部可用空间大小
	 * 
	 * @return
	 * 
	 */
	static public long getAvailableExternalMemorySize() {

		if (externalMemoryAvailable()) {

			File path = Environment.getExternalStorageDirectory();

			StatFs stat = new StatFs(path.getPath());

			long blockSize = stat.getBlockSize();

			long availableBlocks = stat.getAvailableBlocks();

			return availableBlocks * blockSize;

		} else {

			return ERROR;

		}

	}

	/**
	 * @author lijingzuo
	 * 
	 *         Time: 2011-3-23 下午04:23:51
	 * 
	 *         Name:
	 * 
	 *         Description: 外部总空间大小
	 * 
	 * @return
	 * 
	 */
	static public long getTotalExternalMemorySize() {

		if (externalMemoryAvailable()) {

			File path = Environment.getExternalStorageDirectory();

			StatFs stat = new StatFs(path.getPath());

			long blockSize = stat.getBlockSize();

			long totalBlocks = stat.getBlockCount();

			long result = totalBlocks * blockSize;

			if (Log.D) {
				Log.d(TAG, "getTotalExternalMemorySize() -->> " + result);
			}

			return result;

		} else {
			return ERROR;
		}

	}

	/**
	 * @author lijingzuo
	 * 
	 *         Time: 2011-3-23 下午04:24:40
	 * 
	 *         Name:
	 * 
	 *         Description: 把空间大小格式化为字符串
	 * 
	 * @param size
	 * @return
	 * 
	 */
	static public String formatSize(long size) {

		String suffix = null;

		if (size >= 1024) {

			suffix = "KiB";

			size /= 1024;

			if (size >= 1024) {

				suffix = "MiB";

				size /= 1024;

			}

		}

		StringBuilder resultBuffer = new StringBuilder(Long.toString(size));

		int commaOffset = resultBuffer.length() - 3;

		while (commaOffset > 0) {

			resultBuffer.insert(commaOffset, ',');

			commaOffset -= 3;

		}

		if (suffix != null)

			resultBuffer.append(suffix);

		return resultBuffer.toString();

	}

	/**
	 * @author lijingzuo
	 * 
	 *         Time: 2011-3-23 下午04:24:40
	 * 
	 *         Name:
	 * 
	 *         Description: 把空间大小格式化为字符串（小数点）
	 * 
	 * @param size
	 * @return
	 * 
	 */
	static public String formatSize2(long size) {

		String suffix = null;

		float sizeF = Long.valueOf(size).floatValue();

		if (sizeF >= 1024) {

			suffix = "KB";

			sizeF = sizeF / 1024;

			if (sizeF >= 1024) {

				suffix = "MB";

				sizeF = sizeF / 1024;

			}

		}

		String format = new DecimalFormat(".00").format(sizeF);

		StringBuilder resultBuffer = new StringBuilder(format);

		int commaOffset = resultBuffer.indexOf(".") - 3;

		while (commaOffset > 0) {

			resultBuffer.insert(commaOffset, ',');

			commaOffset -= 3;

		}

		if (suffix != null)

			resultBuffer.append(suffix);

		return resultBuffer.toString();

	}

	/**
	 * 清理缓存文件
	 */
	public static void clearCacheFiles() {
		/*
		ArrayList<CacheFile> list = CacheFileTable.getListByClean();
		boolean externalMemoryAvailable = FileService.externalMemoryAvailable();
		for (int i = 0; i < list.size(); i++) {
			CacheFile cacheFile = list.get(i);

			if (cacheFile == null) {
				continue;
			}

			Directory directory = cacheFile.getDirectory();

			if (directory == null) {
				continue;
			}

			if (directory.getSpace() == Directory.INTERNAL || //
					(directory.getSpace() == Directory.EXTERNAL && externalMemoryAvailable == true)) {
				boolean result = cacheFile.getFile().delete();
				if (Log.D) {
					Log.d(TAG, "cacheFile.getName() -->> " + cacheFile.getName());
				}
				if (result) {
					CacheFileTable.delete(cacheFile);
				}
			}
		}

		resetPhoneMemoryImageNum(UN_KNOW);
		resetSdcardImageNum(UN_KNOW);
		*/
	}

	/**
	 * 清理手机内部存储的缓存图片： </br>应用退出时，如果当前使用手机内部存储缓存图片，检测手机内部存储如果小于50M，就清除所有存储的图片，释放用户存储空间。
	 */
	public static void clearInternalCacheImages() {
		final Directory imageDir = getImageDirectory();
		if (null != imageDir //
				&& imageDir.getSpace() == Directory.INTERNAL //
				&& getAvailableInternalMemorySize() < THRESHOLD_IMAGE_CLEAR_SIZE) {

			deleteAllFilseForDirectory(imageDir);
			// 重置手机内存图片中的标志位
			resetPhoneMemoryImageNum(UN_KNOW);
		}
	}

	/**
	 * 清除所有缓存图片，包括SD卡或内部存储的
	 */
	public static void clearAllCacheImages() {

		if (externalMemoryAvailable()) { // 扩展卡可用
			//删除扩展卡的缓存图片
			final Directory imageDir = new Directory(getExternalDirectory(IMAGE_CHILD_DIR), Directory.EXTERNAL);
			deleteAllFilseForDirectory(imageDir);
			// 重置sd卡的图片数量标志位
			resetSdcardImageNum(UN_KNOW);
		}

		// 删除内部存储的缓存图片
		final Directory imageDir = new Directory(getInternalDirectory(IMAGE_CHILD_DIR, false), Directory.INTERNAL);
		deleteAllFilseForDirectory(imageDir);
		// 重置手机内存中图片数量的标志位
		resetPhoneMemoryImageNum(UN_KNOW);
	}

	/**
	 * 删除Directory类指定文件夹下所有文件
	 * @param directory
	 */
	private static void deleteAllFilseForDirectory(Directory directory) {

		if (directory == null) {
			return;
		}

		File dir = directory.getDir();

		if (dir == null || !dir.exists()) {
			return;
		}

		String[] names = dir.list();

		if (names == null) {
			return;
		}

		for (String name : names) {
			File file = new File(dir, name);
			if (file != null && file.exists()) {
				file.delete();
			}
		}
	}

	/**
	 * 目录封装类
	 */
	public static class Directory {

		public static final int INTERNAL = 1;
		public static final int EXTERNAL = 2;

		private File dir;
		private String path;
		private int space;

		public Directory(String path, int space) {
			this(new File(path), space);
		}

		public Directory(File dir, int space) {
			this.dir = dir;
			this.space = space;
		}

		public File getDir() {
			return dir;
		}

		public void setDir(File dir) {
			this.dir = dir;
		}

		public int getSpace() {
			return space;
		}

		public void setSpace(int space) {
			this.space = space;
		}

		public String getPath() {
			if (null == path && null != dir) {
				path = dir.getAbsolutePath();
			}
			return path;
		}

		public void setPath(String path) {
			if (null == getPath() || !getPath().equals(path)) {
				dir = new File(path);
				this.path = path;
			}
		}

	}

	/**
	 * 更改指定文件或目录的读、写、执行权限
	 * 
	 * @param mode
	 *            需要修改的权限
	 * @param file
	 *            文件或文件夹
	 */
	private static void chModFile(String mode, File file) {
		try {
			Runtime.getRuntime().exec("chmod " + mode + " " + file);
		} catch (Exception e) {
			if (Log.D) {
				e.printStackTrace();
				Log.d(TAG, " -->> chModFile mode:" + mode + " file:" + file + " error:" + e.getMessage());
			}
		}
	}

	/**
	 * 保存共享图片文件，被第三方应用可访问的
	 * @param imageFile
	 * @return
	 */
	public static String saveShareImage(File imageFile) {
		String result = null;
		FileGuider savePath = new FileGuider();// 统一文件操作对象
		savePath.setSpace(Directory.INTERNAL);
		savePath.setImmutable(true);
		savePath.setFileName(SHARE_IMAGE_NAME);
		savePath.setMode(Context.MODE_WORLD_READABLE);

		FileOutputStream fileOutputStream = null;// 得到输出流
		FileInputStream fileInputStream = null;// 得到输入流
		try {
			fileOutputStream = FileService.openFileOutput(savePath);
			fileInputStream = new FileInputStream(imageFile);

			// 复制文件
			IOUtil.readAsFile(fileInputStream, fileOutputStream, null, new StopController() {

				@Override
				public void stop() {
				}

				@Override
				public boolean isStop() {
					return false;
				}
			});
			// 得到共享文件名称
			result = getFileAbsolutePath(savePath);
		} catch (Exception e) {
			if (Log.D) {
				e.printStackTrace();
				Log.d(TAG, " saveShareImage-->> " + e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 文件排序机制，按照最后修改时间进行排序（升序，越旧的越在前面）
	 */
	private static class FileSorter implements Comparator<File> {

		@Override
		public int compare(File object1, File object2) {

			if (object1 == null || object2 == null || object1 == object2) {
				return 0;
			}

			long d1 = object1.lastModified();
			long d2 = object2.lastModified();
			if (d1 == d2) {
				return 0;
			} else {
				return d1 > d2 ? 1 : -1;
			}
		}

	}

	/**
	 * 后台图片缓存清理线程 
	 * @author tandingqiang
	 *
	 */
	private static class DeleteOverImageThread extends Thread {

		private File imageDir;

		public DeleteOverImageThread(File imageDir) {
			super();
			this.imageDir = imageDir;
		}

		@Override
		public void run() {
			try {
				deleteOverImageWithDir(imageDir);
			} catch (Throwable e) {
				if (Log.D) {
					e.printStackTrace();
				}
			}

			resetPhoneMemoryImageNum(UN_KNOW);
			resetSdcardImageNum(UN_KNOW);

			deleteOverImageThread = null;
		}
	}

}
