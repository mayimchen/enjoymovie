package com.banking.xc.secure; 

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.banking.xc.utils.Log;

/**
 * Created by IntelliJ IDEA.
 * User: George
 * Date: 11-9-2
 * Time: 下午3:18
 * To change this template use File | Settings | File Templates.
 */
public class DesUtil {
//             private final static Log log = LogFactory.getLog(DesUtil.class);
             private final static String DES = "DES";
             private final static String PADDING="DES/ECB/PKCS5Padding";



             /**

              * 加密

              * @param src 数据源

              * @param key 密钥，长度必须是8的倍数

              * @return  返回加密后的数据

              * @throws Exception

              */

             public static byte[] encrypt(byte[] src, byte[] key)throws Exception {

                     //DES算法要求有一个可信任的随机数源

                     SecureRandom sr = new SecureRandom();

                     // 从原始密匙数据创建DESKeySpec对象

                     DESKeySpec dks = new DESKeySpec(key);

                     // 创建一个密匙工厂，然后用它把DESKeySpec转换成

                     // 一个SecretKey对象

                     SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

                     SecretKey securekey = keyFactory.generateSecret(dks);

                     // Cipher对象实际完成加密操作

                     Cipher cipher = Cipher.getInstance(PADDING);

                     // 用密匙初始化Cipher对象

                     cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

                     // 现在，获取数据并加密

                     // 正式执行加密操作

                     return cipher.doFinal(src);

                  }


                  /**

                  * 解密

                  * @param src 数据源

                  * @param key 密钥，长度必须是8的倍数

                  * @return   返回解密后的原始数据

                  * @throws Exception

                  */

                  public static byte[] decrypt(byte[] src, byte[] key)throws Exception {

                     // DES算法要求有一个可信任的随机数源

                     SecureRandom sr = new SecureRandom();

                     // 从原始密匙数据创建一个DESKeySpec对象

                     DESKeySpec dks = new DESKeySpec(key);

                     // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成

                     // 一个SecretKey对象

                     SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

                     SecretKey securekey = keyFactory.generateSecret(dks);

                     // Cipher对象实际完成解密操作

                     Cipher cipher = Cipher.getInstance(PADDING);

                     // 用密匙初始化Cipher对象

                     cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

                     // 现在，获取数据并解密

                     // 正式执行解密操作

                     return cipher.doFinal(src);

                  }

               /**

                * 密码解密

                * @param data

                * @return

                * @throws Exception

                */

               public final static String decrypt(String data,String key){

                  try {

                   return new String(decrypt(Base64.decode(data.getBytes()),

                      key.getBytes()));

                 }catch(Exception e) {
                       Log.e("Exception -->DesUtil " , "Exception -->DesUtil");
                       e.printStackTrace();
                 }

                 return null;

               }

    /**

                * 密码解密

                * @param data

                * @return

                * @throws Exception

                */

               public final static String decrypt(String data,String key, String charset){

                  try {

                   return new String(decrypt(Base64.decode(data.getBytes()),

                      key.getBytes()), charset);

                 }catch(Exception e) {
                	 Log.e("Exception -->DesUtil " , "Exception -->DesUtil");
                	 e.printStackTrace();
                 }

                 return null;

               }

               /**

                * 密码加密

                * @param code

                * @return

                * @throws Exception

                */

               public final static String encrypt(String code,String key){

                 try {

                   return Base64.encodeBytes(encrypt(code.getBytes("utf-8"), key.getBytes()));
                 }catch(Exception e) {
                	 Log.e("Exception -->DesUtil " , "Exception -->DesUtil");
                	 e.printStackTrace();
                 }

                 return null;

               }

    public static void main(String[] args) {
//       System.out.print( DesUtil.encrypt("mGsAmeU/lQA=", "1234567890abcdEfg"));
        System.out.println(decrypt("zkg3mzuPDWK7Y5lm+AEEDZP9il67Zf+BH6xeA7tmedn7evkOJi4i51SLzlyRjSldT9hRsBRQoc4y6yuAUc3KmtLMYy1rZbP/m8VJrcu6fKOuuoc68SpJkH6hRRLmC2do5XiIuCpLX0jBMOVw8uykvmVN7XIbLGXdQy4KrYPSx+2r/cd7DbB9iERC3zN5KEc+xzRxk7WoJNS4OEHovMjjsWr2wz4BXN3kAje053LLOljR4YErFUid+zAYSK9NpRIIW4RZDTX4MNEzWMBryueDO3+xGXSPvib7IOvKnVEtZKes7XDMNpIE5uQ4jvGvBWtYNxDLD1S/i6weLlEWDdojccst2G+mJMaafUNtBvhUSQlq7e61F3WHkkEgdRkEfli3RirXnVraSpdyE5Ok2YEPbW0o1o+3bDYwbNFxsb76p3iLhklOnlfZRu2b5OplNx7qImEa+DPtYNCRQV19EIgSMm8lwXzPArP6r7O7l5H7c1Be8Nk8nRT2QK66hzrxKkmQ0vZnVA1Bt6RPYwr1VAKY8z6Gw0PqkrtQF8QQBAdN3WlqlgWQEYpi3IMom1xLWEVeA31VeA/OcuQr2ajog75hi+apAYd7pW9KCiGzg7UIXJbuA9l3Bd1Jqs3b4LWIn/xYjJzZ/MdG3owxDh8VamudzQlVHFoS47IPZJUgrUcLtXvlaPsa7fDGafZnBafQ3IwH52WtLv9Whob0/2uO28OJEHTiR3jOFtexo32LMehNz95s36qrPmVukmxOSbJrJMpmxbZlJ1EbT36lxY2t2QqImAZVXLtwQv44kNEIPfO02k2huTldx4yor+JXc66Rkcq67OmiRhwAdZIvbkFxAeDdwGLbIgUEKgEcl5s0C4RMhtI=", "LJc4ZEnVXvtuese1RWsIs1Tml55wDvJU", "utf-8"));
    }

	/**
	 * Convert byte[] string to 16进制
	 * 
	 * @param src
	 * @return
	 */
	public static String bytesTo16HexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return "";
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
}
