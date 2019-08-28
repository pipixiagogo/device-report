package com.zh.device.MyUtils;

import com.zh.device.util.ByteUtil;

import java.io.*;

public class ParseFiles {
    public static final String CERT_PATH="Cert";
    public static final String ROOT_CERT_PATH="root_cert.pem";
    public static final String PUBLIC_CERT_PATH="public_cert.pem";
    public static final String PRIVATE_CERT_PATH="private_cert.pem";


    public static void main(String[] args) {
        parseSSLCert();
    }
    
    public static void parseSSLCert(){
        OutputStream out1 =null;
        OutputStream out2=null;
        OutputStream out3=null;
        BufferedInputStream inputStream  =null;
        InputStream stream=null;
        try {
            stream = ParseFiles.class.getClassLoader().getResourceAsStream(CERT_PATH);
            inputStream  = new BufferedInputStream(stream);
            out1 = new FileOutputStream(ROOT_CERT_PATH);
            out2 = new FileOutputStream(PUBLIC_CERT_PATH);
            out3 = new FileOutputStream(PRIVATE_CERT_PATH);
            byte[] checkSum=new byte[24];
            inputStream.read(checkSum);
            byte[] magicNum=new byte[4];
            inputStream.read(magicNum);
            System.out.println(new String(magicNum));
            byte[]totalSize=new byte[4];
            inputStream.read(totalSize);
            int totalLength = ByteUtil.toIntLE(totalSize);
            System.out.println("数据总大小"+totalLength);
            byte[] fileId1=new byte[2];
            fileId1= changeBytes(fileId1);
           inputStream.read(fileId1);
//            byte[]FF={(byte)0xFF,(byte)0xFE};
//            for(byte FFF:FF){
//                System.out.println("FFF"+FFF);
//            }
            byte[] certId1=new byte[2];
            inputStream.read(certId1);
            byte[] certLength1=new byte[2];
            inputStream.read(certLength1);
            certLength1=changeBytes(certLength1);
            System.out.println("cert"+ByteUtil.toShort(certLength1));
            byte[] cert1 = new byte[ByteUtil.toShort(certLength1)];
            inputStream.read(cert1);
            out1.write(cert1);

            byte[] publicKey=new byte[2];
            inputStream.read(publicKey);
            byte[] publicKeyLength=new byte[2];
            inputStream.read(publicKeyLength);
            System.out.println(ByteUtil.toShort(publicKeyLength));
//            byte[] publickMess=new byte[ByteUtil.toShortLE(publicKeyLength)];
//
//            inputStream.read(publickMess);
//            out2.write(publickMess);
//
//            byte[] privateKey=new  byte[2];
//            inputStream.read(privateKey);
//            byte[] privateKeyLength=new byte[2];
//            inputStream.read(privateKeyLength);
//
//            byte[] privateCert=new byte[ByteUtil.toShortLE(privateKeyLength)];
//            System.out.println(ByteUtil.toShortLE(privateKeyLength));
//            inputStream.read(privateCert);
//            out3.write(privateCert);
        }catch (Exception e){
            System.out.println("证书加载失败");
        }finally {
            try {
                out1.close();
                out2.close();
                out3.close();
                inputStream.close();
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static byte[] changeBytes(byte[] a){
        byte[] b = new byte[a.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = a[b.length - i - 1];
        }
        return b;
    }
}
