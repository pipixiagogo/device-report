package com.zh.device.util;

public class MsgBuilder {

    private static final byte[]SOF=new byte[]{(byte)0xbb,(byte)0xcd};
    private static final int BID_LENGTH=4;
    private static final int MAX_SERVICE_DATA_LEN = 0x0000ffff;

    private byte[] bid;
    private byte[] version;
    private byte[] aid;
    private byte[] mid;
    private byte[] serviceData;
    private byte[] serviceDataLength;

    public  MsgBuilder setBid(byte[] bid){
        if(bid.length!=BID_LENGTH){
            throw  new RuntimeException("BID长度不符合要求");
        }
        this.bid=bid;
        return this;
    }
    public  MsgBuilder setVersion(byte version){
        this.version=new byte[]{version};
        return this;
    }
    public  MsgBuilder setAid(byte aid){
        this.aid=new byte[]{aid};
        return this;
    }
    public  MsgBuilder setMid(byte mid){
        this.mid=new byte[]{mid};
        return this;
    }
    public MsgBuilder setServiceData(byte[]data){
        this.serviceData=data;
        setServiceDataLength(data.length);
        return this;
    }

    private MsgBuilder setServiceDataLength(int length) {
        if(length>MAX_SERVICE_DATA_LEN||length<0){
            throw new RuntimeException("数据长度有误");
        }
        byte[] bytes = ByteUtil.fromInt(length);
        byte[] lengthByte=new byte[2];
        System.arraycopy(bytes,2,lengthByte,0,lengthByte.length);
        this.serviceDataLength=lengthByte;
        return this;
    }

    public byte[] getBid() {
        return bid;
    }

    public byte[] getVersion() {
        return version;
    }

    public byte[] getAid() {
        return aid;
    }

    public byte[] getMid() {
        return mid;
    }

    public byte[] getServiceData() {
        return serviceData;
    }

    public byte[] getServiceDataLength() {
        return serviceDataLength;
    }

    public byte[] build(){
        if(aid==null||mid==null||bid==null||serviceDataLength==null||version==null){
            throw new RuntimeException("创建错误");
        }
        return PlusArrayUtils.combine(SOF,bid,version,aid,mid,serviceDataLength,serviceData);
    }

}
