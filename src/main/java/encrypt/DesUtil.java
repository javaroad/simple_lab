package encrypt;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;

/**
 * DES加密工具类
 */
public class DesUtil {

	private final static String ENCRYPT_TYPE = "DES";
	private final static String DEFAULTKEY = "@4!@#$%@";// 字符串默认键值
	public final static String SPLITKEY = "_s_";// 字符串默认键值

	/**
	 * 加密字节数组
	 * 
	 * @param arr
	 *            需加密的字节数组
	 * @return 加密后的字节数组
	 * @throws Exception
	 */
	private static byte[] encryptStr(byte[] arr) throws Exception {
		Cipher encryptCipher = null;// 加密工具
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(DesUtil.DEFAULTKEY.getBytes());
		encryptCipher = Cipher.getInstance(ENCRYPT_TYPE);
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		return encryptCipher.doFinal(arr);
	}

	/**
	 * 加密字符串
	 * 
	 * @param strIn
	 *            需加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String encrypt(String strIn) throws Exception {
		return StrConvertUtil.byteArrToHexStr(encryptStr(strIn.getBytes()));
	}

	/**
	 * 解密字节数组
	 * 
	 * @param arr
	 *            需解密的字节数组
	 * @return 解密后的字节数组
	 * @throws Exception
	 */
	private static byte[] decryptStr(byte[] arr) throws Exception {

		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Cipher decryptCipher = null;// 解密工具
		decryptCipher = Cipher.getInstance(ENCRYPT_TYPE);
		Key key = getKey(DesUtil.DEFAULTKEY.getBytes());
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
		return decryptCipher.doFinal(arr);
	}

	/**
	 * 解密字符串
	 * 
	 * @param strIn
	 *            需解密的字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public static String decrypt(String strIn) throws Exception {
		return new String(decryptStr(StrConvertUtil.hexStrToByteArr(strIn)));
	}

	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位。不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param arrBTmp
	 *            构成该字符串的字节数组
	 * @return 生成的密钥
	 */
	private static Key getKey(byte[] arrBTmp) {
		byte[] arrB = new byte[8];// 创建一个空的8位字节数组（默认值为0）
		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, ENCRYPT_TYPE);// 生成密钥
		return key;
	}

	public static void main(String[] args) {
		try {
			System.out.println(DesUtil.encrypt("12345678!@#$%^&").equals(
					"126ddc46aa966bc12b4154804b42974b"));
			System.out.println(DesUtil.decrypt(
					"126ddc46aa966bc12b4154804b42974b").equals(
					"12345678!@#$%^&"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}