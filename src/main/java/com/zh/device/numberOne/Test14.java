package com.zh.device.numberOne;

import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Test14 {
    private static String transformation = "AES/ECB/PKCS5Padding";
    private static String algorithm = "AES";

    private static String secret="1234567890123456";
    public static void main(String[] args) {
        byte[] bytes = encryptWithoutBase64("pipixia", secret.getBytes());
        String secreted = Base64Utils.encodeToString(bytes);
        System.out.println(secreted);
        String unsecreted = decryptWithBase64(secreted, secret);
        System.out.println(unsecreted);
    }
    //加密
    public static byte[] encryptWithoutBase64(String content, byte[] password) {
        try {
            byte[] byteContent = content.getBytes("utf-8");
            return encryptWithoutBase64(byteContent,password);
        }  catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //加密
    public static byte[] encryptWithoutBase64(byte[] content, byte[] password) {
        try {
            if( password == null || password.length != 16 )
                throw new RuntimeException("password error");

            SecretKeySpec key = new SecretKeySpec(password, algorithm);
            IvParameterSpec iv = new IvParameterSpec(password);
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);// 加密
            return result;
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }  catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密
    public static String decryptWithBase64(String content,String password){
        byte[] bs = Base64Utils.decodeFromString( content );
        byte[] ubs = decryptWithoutBase64(bs,password.getBytes());
        if( ubs != null && ubs.length > 0 ){
            try {
                return new String(ubs,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
    //解密
    public static byte[] decryptWithoutBase64(byte[] content, byte[] key) {
        try {
            if( key.length != 16 ){
                throw new RuntimeException();
            }
            SecretKeySpec skey = new SecretKeySpec(key, algorithm);
            IvParameterSpec iv = new IvParameterSpec(key);
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, skey);// 初始化
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
