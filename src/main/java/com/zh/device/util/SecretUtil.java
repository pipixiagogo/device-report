//package com.zh.device.util;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.tomcat.util.codec.binary.Base64;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;
//
//public class SecretUtil {
//    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
//    private static final String CHAR_SET = "UTF-8";
//    private static final String KEY_ALGORITHM = "AES";
//    public static String encrypt(String content, String password) {
//        if (StringUtils.isAnyEmpty(content, password)) {
//            return null;
//        }
//        try {
//            //创建密码器
//            byte[] psw = ByteUtil.parseHexStringToArray(password);
//            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
//            byte[] byteContent = content.getBytes(CHAR_SET);
//            SecretKeySpec spec = new SecretKeySpec(psw, KEY_ALGORITHM);
//            //初始化为加密密码器
//            cipher.init(Cipher.ENCRYPT_MODE,spec);
//            byte[] encryptByte = cipher.doFinal(byteContent);
//            return Base64.encodeBase64String(encryptByte);
//        } catch (Exception e) {
//
//        }
//        return null;
//    }
//    public static String decrypt(String encryptContent, String password) {
//        if (StringUtils.isAnyEmpty(encryptContent, password)) {
//            return null;
//        }
//        Cipher cipher = null;
//        try {
//            cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
//            byte[] psw = ByteUtil.parseHexStringToArray(password);
//            SecretKeySpec spec = new SecretKeySpec(psw, KEY_ALGORITHM);
//            //设置为解密模式
//            cipher.init(Cipher.DECRYPT_MODE, spec);
//            //执行解密操作
//            byte[] result = cipher.doFinal(Base64.decodeBase64(encryptContent));
//            return new String(result, CHAR_SET);
//        } catch (Exception e) {
//
//        }
//        return null;
//    }
//
//
////    public static void main(String[] args) {
////        String password="31323334353637383930313233343536";
////        String encrypt = encrypt("RDBSN=1234567890123456789012,BMUPCNT=3,BMUPSNS=12345678|12345678|123456,TIMESTAMP=1234567890", password);
////        System.out.println(encrypt);
////        String decrypt = decrypt(encrypt, password);
////        System.out.println(decrypt);
////    }
//
//}
