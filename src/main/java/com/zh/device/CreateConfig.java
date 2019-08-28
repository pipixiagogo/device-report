package com.zh.device;

import com.zh.device.config.PropertiesConfig;
import com.zh.device.util.ByteUtil;
import com.zh.device.util.Md5;
import com.zh.device.util.PlusArrayUtils;
import com.zh.device.util.StringUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CreateConfig {
    public static final int MAGIC_NUM = ByteUtil.toInt( new byte[]{(byte) 0xfc, (byte) 0xfc, (byte) 0xfc, (byte) 0xfc} );
    private static final byte[] FRE_COL_ON_BYTE = new byte[]{0x01,0x00};
    private static final byte[] FRE_COL_OFF_BYTE = new byte[]{0x00,0x00};
    private static final int FIXED_LEN = 113;
    private static final int MAX_SHORT_LEN = ByteUtil.toInt( new byte[]{0x00,0x00, (byte) 0xff, (byte) 0xff} );
    private static final int MAX_DOMAIN_LEN = 9999;
    private static final int CHECK_SUM_LEN = 24;
    private static final byte[] SERVER_ID = new byte[]{0x00,0x00};
    private static final byte[] SERVER_ID_SSL = new byte[]{0x01,0x00};
    private static final byte[] BASE_ID = new byte[]{0x00,0x10};
    private static final byte[] COLLECT_OBJ_ID = new byte[]{0x01,0x10};
    private static final byte[] CAN_COLLECT_CONFIG_ID = new byte[]{0x02,0x10};
    private static final short CAN_COLLECT_CONFIG_LEN = 15;
    private static final byte[] FILE_ID = new byte[]{0x00,0x00};
    private static final int SERVER_DATA_LEN_MIN = 10;
    private static final int SERVER_SSL_DATA_LEN_MIN = 26;
    private static final int BASE_DATA_LEN_MIN = 6;
    private static final int COLLECT_OBJ_DATA_LEN_MIN = 2;
    private static final byte[] RETAIN_BYTES = new byte[]{0x00,0x00};
    private static final long NO_EXTENDS_FILE_SIZE = 97;
    private static final String domain="11hhw.tpddns.cn";
    private static final String msgidPath="";
    public static final String DEFAULT_MSGID_PATH = "msgids.txt";
    private Set<Long> msgIds;
    public Object lock2=new Object();
    public static void main(String[] args)throws Exception {
        CreateConfig createConfig = new CreateConfig();
        byte[] config = createConfig.config();
        byte[] checkSum = createConfig.calCheckSum(config);
        byte[] combine = PlusArrayUtils.combine(checkSum, config);
        System.out.println(ByteUtil.toHexString(combine));
        File file = new File("D:/config.bin");
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream outputStream=new FileOutputStream(file);
        outputStream.write(combine);
        outputStream.close();
    }

    private byte[] calCheckSum(byte[] file) {
        Md5 md5 = Md5.getInstance();
        byte[] checksum = md5.md5_16_byte( file );
        int remain = CHECK_SUM_LEN - checksum.length;
        if( remain > 0 ){
            byte[] remainbytes = new byte[remain];
            checksum = PlusArrayUtils.combine(checksum,remainbytes);
        }
        return checksum;
    }

    private byte[] config(){
        boolean extendsConfig =false;
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt( MAGIC_NUM );
        if( extendsConfig ){
            buf.writeIntLE( (int)getFileSize() );
        }else {
            buf.writeIntLE( (int)getFileSizeWithoutExtends() );
        }
        buf.writeBytes( FILE_ID );
        //构建服务器基本参数
        int compatiblePort = 18835;
        buf.writeBytes( SERVER_ID );
        buf.writeShortLE( getServerDataLen() );
        buf.writeShortLE( 300 );
        buf.writeShortLE( 60 );
        buf.writeShortLE( 81 );
        buf.writeShortLE( compatiblePort );
        buf.writeShortLE( this.getDomainLen() );
        buf.writeBytes( this.domain.getBytes() );
        //构建服务器扩展参数
        buf.writeBytes(SERVER_ID_SSL);
        buf.writeShortLE(getServerSSLDataLen());
        buf.writeShortLE( 300 );
        buf.writeShortLE( 60 );
        buf.writeShortLE( 81 );
        buf.writeShortLE( 8883 );
        buf.writeShortLE(0);//https端口 保留
        buf.writeShortLE(0);//保留
        buf.writeShortLE(0);//保留
        buf.writeShortLE(0);//保留
        buf.writeShortLE(0);//保留
        buf.writeShortLE(0);//保留
        buf.writeShortLE(0);//保留
        buf.writeShortLE(0);//保留
        buf.writeShortLE( this.getDomainLen() );
        buf.writeBytes( this.domain.getBytes() );
//        if( extendsConfig ){
        //是否扩展时候在修改该配置生成配置文件
//            //构建基本参数
//            buf.writeBytes( BASE_ID );
//            buf.writeShortLE( getBaseDataLen() );
//            buf.writeShortLE( 30 );
//            buf.writeShortLE( 4 );
//            if( true ){
//                buf.writeBytes( FRE_COL_ON_BYTE );
//            }else{
//                buf.writeBytes( FRE_COL_OFF_BYTE );
//            }
//            //构建采集对象
//            buf.writeBytes( COLLECT_OBJ_ID );
//            buf.writeShortLE( getCollectObjDataLen() );
//            buf.writeShortLE( getCollectNum() );
//            if( this.objs != null ){
//                for( CollectObject co : objs ){
//                    buf.writeIntLE((int) co.getCanid());
//                    buf.writeShortLE( co.getCollectTime() );
//                    buf.writeBytes( RETAIN_BYTES );
//                }
//            }
//        }
        buf.writeBytes( CAN_COLLECT_CONFIG_ID );
        buf.writeShortLE(getCanCollectConfigLen());
        buf.writeShortLE(10);//概要数据采集间隔
        buf.writeShortLE(4);//概要数据变频采集间隔
        buf.writeShortLE(1);//调试数据采集间隔
        buf.writeShortLE(60);//详细数据采集间隔
        buf.writeShortLE(10);//扩展信息采集间隔
        fillBoolean(buf,true);//概要数据采集配置
        fillBoolean(buf, false);//详细数据采集配置
        fillBoolean(buf, false);//扩展数据采集配置
        fillBoolean(buf, false);//调试数据采集配置
        fillBoolean(buf, true);
        byte[] file = new byte[ buf.readableBytes() ];
        buf.readBytes( file );
        return file;
    }
//    public int getCollectObjDataLen(){
//        if( objs != null ){
//            return COLLECT_OBJ_DATA_LEN_MIN + CollectObject.SIZE * objs.size();
//        }else {
//            return COLLECT_OBJ_DATA_LEN_MIN;
//        }
//    }
    public int getBaseDataLen(){
        return BASE_DATA_LEN_MIN;
    }
    private int getServerSSLDataLen(){
        if( domain == null ){
            return SERVER_SSL_DATA_LEN_MIN;
        }else {
            return SERVER_SSL_DATA_LEN_MIN + domain.getBytes().length;
        }
    }

    public long getFileSize() {
        long fileSize = FIXED_LEN;
        if( domain != null ) {
            fileSize = fileSize + domain.getBytes().length * 2;
        }
//        if( objs != null ){
//            fileSize = fileSize + objs.size() * CollectObject.SIZE;
//        }
        return fileSize;
    }

    public long getFileSizeWithoutExtends(){
        long fileSize = NO_EXTENDS_FILE_SIZE;
        if( domain != null ) {
            fileSize = fileSize + domain.getBytes().length * 2;
        }
        return fileSize;
    }
    private void fillBoolean(ByteBuf buf, boolean flag){
        if( flag ){
            buf.writeByte(1);
        }else {
            buf.writeByte(0);
        }
    }

    private int getServerDataLen(){
        if( domain == null ){
            return SERVER_DATA_LEN_MIN;
        }else {
            return SERVER_DATA_LEN_MIN + domain.getBytes().length;
        }
    }

    public int getDomainLen() {
        if(!StringUtil.isEmpty(domain)){
            return domain.getBytes().length;
        }
        return 0;
    }

    public int getCanCollectConfigLen() {
        return CAN_COLLECT_CONFIG_LEN;
    }

    public Set<Long> getMsgIds() {
        if ( msgIds == null ){
            synchronized (lock2){
                if ( msgIds == null ){
                    Set<Long> set = new HashSet<>();
                    InputStream in = null;
                    try {
                        if( this.msgidPath == null || "".equalsIgnoreCase( this.msgidPath ) ){
                            in = PropertiesConfig.class.getClassLoader().getResourceAsStream( DEFAULT_MSGID_PATH );
                        }else {
                            File f = new File( msgidPath );
                            in = new FileInputStream( f );
                        }
                        if( in == null )
                            throw new RuntimeException("默认msgid 资源读取失败。");
                        BufferedReader reader = new BufferedReader( new InputStreamReader( in ) );
                        for( String line = reader.readLine(); line != null; line = reader.readLine() ){
                            if( line.length() != 10 ){
                                continue;
                            }
                            byte[] bytes = ByteUtil.parseHexStringToArray( line.substring(2) );
                            set.add( ByteUtil.toUnsignedInt( bytes ) );
                        }
                        this.msgIds = Collections.unmodifiableSet( set );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }  finally {
                        if( in != null ){
                            try {
                                in.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return this.msgIds;
    }
}
