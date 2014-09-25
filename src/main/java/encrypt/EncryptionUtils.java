//package encrypt;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.text.DecimalFormat;
//import java.util.Random;
//
//import org.apache.commons.lang3.StringUtils;
//
//import sun.misc.BASE64Encoder;
//
///**
// * Created by hongyu.zhuang
// * on 5/4/14 12:38 PM
// */
//public class EncryptionUtils {
//
//    public static String md5(String plainText ) {
//        if (StringUtils.isBlank(plainText)) {
//            return null;
//        }
//
//        MessageDigest md = createMessageDigest(EncryptionType.MD5);
//        if (null == md)
//            return null;
//
//        md.update(plainText.getBytes());
//        byte b[] = md.digest();
//
//        return byteToString(b);
//    }
//
//    public static String sha1(String plainText ) {
//        if (StringUtils.isBlank(plainText)) {
//            return null;
//        }
//
//        MessageDigest md = createMessageDigest(EncryptionType.SHA1);
//        if (null == md)
//            return null;
//
//        md.update(plainText.getBytes());
//        byte b[] = md.digest();
//
//        return byteToString(b);
//    }
//
//
//    private static String SECRET_KEY = "@4!@#$%@";
//    public static String encryPwdForTuan(String pwd, String userName, String key) {
//        if (StringUtils.isBlank(pwd) || StringUtils.isBlank(userName) || StringUtils.isBlank(key)) {
//            return null;
//        }
//
//        return sha1(md5(pwd + SECRET_KEY) + userName + key);
//    }
//
//
//    private static MessageDigest createMessageDigest(EncryptionType encryptionType) {
//
//        MessageDigest md = null;
//        try {
//            md = MessageDigest.getInstance(encryptionType.getType());
//        } catch (NoSuchAlgorithmException e) {
//            //e.printStackTrace();
//        }
//
//        return md;
//    }
//
//    public static String randForNine() {
//        Random r = new Random();
//        return new DecimalFormat("0.00000000").format(r.nextDouble() * 10);
//    }
//
//
//
//    public static String base64(String plainText) {
//        if (StringUtils.isBlank(plainText)) {
//            return plainText;
//        }
//
//       return (new BASE64Encoder()).encodeBuffer(plainText.getBytes());
//    }
//
//    final static String byteToString(byte[] digest) {
//        if (null == digest)
//            return null;
//
//        int i;
//        StringBuffer buf = new StringBuffer("");
//        for (int offset = 0; offset < digest.length; offset++) {
//            i = digest[offset];
//            if(i<0) i+= 256;
//            if(i<16)
//                buf.append("0");
//
//            buf.append(Integer.toHexString(i));
//        }
//
//        //System.out.println("result: " + buf.toString());//32位的加密
//        //System.out.println("result: " + buf.toString().substring(8,24));//16位的加密
//
//      return buf.toString();
//    }
//
//
//    public static void main(String[] args) {
//        Random r = new Random();
//        System.out.println(new DecimalFormat("0.00000000").format(r.nextDouble() * 10));
//        Integer i1 = new Integer(1);
//        Integer i2 = new Integer(1);
//        System.out.println(i1.intValue() == i2);
//
//
//    }
//
//
//}
