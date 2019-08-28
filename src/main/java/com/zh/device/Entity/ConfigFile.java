package com.zh.device.Entity;

import com.bugull.mongo.SimpleEntity;
import com.bugull.mongo.annotations.EmbedList;
import com.bugull.mongo.annotations.Entity;
import com.zh.device.util.ByteUtil;
import com.zh.device.util.Md5;
import com.zh.device.util.PlusArrayUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Set;

@Entity
public class ConfigFile extends SimpleEntity {
    public static final byte[] FRESWITCH_ON = new byte[]{(byte) 0x01, (byte) 0x00};
    public static final byte[] FRESWITCH_OFF = new byte[]{(byte) 0x00, (byte) 0x00};
    public static final byte[] REMAIN_BYTE = new byte[]{(byte) 0x00, (byte) 0x00};

    public static final int MAGIC_NUM = ByteUtil.toInt(new byte[]{(byte) 0xfc, (byte) 0xfc, (byte) 0xfc, (byte) 0xfc});
    public static final int MAX_LENGTH = ByteUtil.toInt(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0xff});
    public static final int CHECKSUM_LENGTH = 24;
    public static final byte[] HEARD_FILE_ID = new byte[]{(byte) 0x00, (byte) 0x00};
    public static final byte[] CONFIG_FILE_ID = new byte[]{(byte) 0x00, (byte) 0x00};
    public static final byte[] CONFIG_BASIC_ID = new byte[]{(byte) 0x00, (byte) 0x10};
    public static final byte[] CONFIG_Object_ID = new byte[]{(byte) 0x01, (byte) 0x10};
    public static final byte[] CONFIG_can_ID = new byte[]{(byte) 0x02, (byte) 0x10};
    public static final int EXTEND_FILE_LENGTH = 81;
    public static final long WITHOUT_EXTEND_FILE_LENGTH = 67;

    public static final boolean EXTEND = false;

    public static final int BASIC_LENGTH = 10;
    public static final int BASIC_SERVER_LENGTH = 14;
    public static final int COLLECTION_LENGTH = 4;
    public static final int CAN_COLLECTION_LENGTH = 19;

    private byte[] checksum;
    private String deviceName;
    private int reportInterval;
    private int baseReportInterval;
    private int httpPort;
    private int mqttPort;
    private String domain;

    private int canCollectionInterval;
    private int freCollectionInterval;
    private boolean freCollectionSwitch;
    @EmbedList
    private Set<CollectionObject> objs;

    private int generalInterval;
    private int generalPreInterval;
    private int debugInterval;
    private int detailInterval;
    private int extendInterval;
    private boolean generalIntervalSwitch;
    private boolean debugIntervalSwitch;
    private boolean detailIntervalSwitch;
    private boolean extendIntervalSwitch;
    private boolean preIntervalSwitch;

    private byte[] fileBytes;


    public byte[] getChecksum() {
        return checksum;
    }

    public void setCheckSum(byte[] checksum) {
        if (checksum.length > CHECKSUM_LENGTH) {
            throw new RuntimeException("checSum长度超出范围");
        }
        this.checksum = checksum;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getReportInterval() {
        return reportInterval;
    }

    public void setReportInterval(int reportInterval) {
        this.reportInterval = reportInterval;
    }

    public void setReportingInterval(int reportInterval) {
        if (reportInterval > MAX_LENGTH) {
            throw new RuntimeException("上报间隔长度超出范围");
        }
        this.reportInterval = reportInterval;
    }

    public int getBaseReportInterval() {
        return baseReportInterval;
    }

    public void setBaseReportInterval(int baseReportInterval) {
        this.baseReportInterval = baseReportInterval;
    }

    public void setBasicReportInterval(int baseReportInterval) {
        if (baseReportInterval > MAX_LENGTH) {
            throw new RuntimeException("基本信息上报间隔长度超出范围");
        }
        this.baseReportInterval = baseReportInterval;
    }

    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(int httpPort) {
        if (httpPort > MAX_LENGTH) {
            throw new RuntimeException("HTTP端口超出范围");
        }
        this.httpPort = httpPort;
    }

    public int getMqttPort() {
        return mqttPort;
    }

    public void setMqttPort(int mqttPort) {
        if (mqttPort > MAX_LENGTH) {
            throw new RuntimeException("MQTT端口超出范围");
        }
        this.mqttPort = mqttPort;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getCanCollectionInterval() {
        return canCollectionInterval;
    }

    public void setCanCollectionInterval(int canCollectionInterval) {
        if (canCollectionInterval > MAX_LENGTH) {
            throw new RuntimeException("CAN采集间隔超出范围");
        }
        this.canCollectionInterval = canCollectionInterval;
    }

    public int getFreCollectionInterval() {
        return freCollectionInterval;
    }

    public void setFreCollectionInterval(int freCollectionInterval) {
        if (freCollectionInterval > MAX_LENGTH) {
            throw new RuntimeException("变频采集间隔超出范围");
        }
        this.freCollectionInterval = freCollectionInterval;
    }

    public boolean isFreCollectionSwitch() {
        return freCollectionSwitch;
    }

    public void setFreCollectionSwitch(boolean freCollectionSwitch) {
        this.freCollectionSwitch = freCollectionSwitch;
    }

    public Set<CollectionObject> getObjs() {
        return objs;
    }

    public void setObjs(Set<CollectionObject> objs) {
        this.objs = objs;
    }

    public int getGeneralInterval() {
        return generalInterval;
    }

    public void setGeneralInterval(int generalInterval) {
        if (generalInterval > MAX_LENGTH) {
            throw new RuntimeException("概要数据采集间隔超出范围");
        }
        this.generalInterval = generalInterval;
    }

    public int getGeneralPreInterval() {
        return generalPreInterval;
    }

    public void setGeneralPreInterval(int generalPreInterval) {
        if (generalPreInterval > MAX_LENGTH) {
            throw new RuntimeException("概要数据变频采集间隔超出范围");
        }
        this.generalPreInterval = generalPreInterval;
    }

    public int getDebugInterval() {
        return debugInterval;
    }

    public void setDebugInterval(int debugInterval) {
        if (debugInterval > MAX_LENGTH) {
            throw new RuntimeException("调试数据采集间隔超出范围");
        }
        this.debugInterval = debugInterval;
    }

    public int getDetailInterval() {
        return detailInterval;
    }

    public void setDetailInterval(int detailInterval) {
        if (detailInterval > MAX_LENGTH) {
            throw new RuntimeException("详细数据采集间隔超出范围");
        }
        this.detailInterval = detailInterval;
    }

    public int getExtendInterval() {
        return extendInterval;
    }

    public void setExtendInterval(int extendInterval) {
        if (extendInterval > MAX_LENGTH) {
            throw new RuntimeException("扩展信息采集间隔超出范围");
        }
        this.extendInterval = extendInterval;
    }

    public boolean isGeneralIntervalSwitch() {
        return generalIntervalSwitch;
    }

    public void setGeneralIntervalSwitch(boolean generalIntervalSwitch) {
        this.generalIntervalSwitch = generalIntervalSwitch;
    }

    public boolean isDebugIntervalSwitch() {
        return debugIntervalSwitch;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public void setDebugIntervalSwitch(boolean debugIntervalSwitch) {
        this.debugIntervalSwitch = debugIntervalSwitch;
    }

    public boolean isDetailIntervalSwitch() {
        return detailIntervalSwitch;
    }

    public void setDetailIntervalSwitch(boolean detailIntervalSwitch) {
        this.detailIntervalSwitch = detailIntervalSwitch;
    }

    public boolean isExtendIntervalSwitch() {
        return extendIntervalSwitch;
    }

    public void setExtendIntervalSwitch(boolean extendIntervalSwitch) {
        this.extendIntervalSwitch = extendIntervalSwitch;
    }

    public boolean isPreIntervalSwitch() {
        return preIntervalSwitch;
    }

    public void setPreIntervalSwitch(boolean preIntervalSwitch) {
        this.preIntervalSwitch = preIntervalSwitch;
    }

    public long getFileLengthWithOutExtend() {
        long fileSize = WITHOUT_EXTEND_FILE_LENGTH;
        if (domain != null) {
            fileSize += domain.getBytes().length;
        }
        return fileSize;
    }

    public long getFileLength() {
        long fileSize = EXTEND_FILE_LENGTH;
        if (domain != null) {
            fileSize += domain.getBytes().length;
        }
        if (objs != null && objs.size() > 0) {
            fileSize += objs.size() * CollectionObject.COLLECTION_OBJ;
        }
        return fileSize;
    }


    public void update() {
        byte[] fileByte = getConfigFleWithoutChecSum();
        this.checksum = cal_MD5Sum(fileByte);
        this.fileBytes = PlusArrayUtils.combine(checksum, fileByte);
    }

    private byte[] cal_MD5Sum(byte[] fileByte) {
        Md5 md5 = Md5.getInstance();
        byte[] md5_16_byte = md5.md5_16_byte(fileByte);
        int remain = CHECKSUM_LENGTH - md5_16_byte.length;
        if (remain > 0) {
            byte[] remainByte = new byte[remain];
            return PlusArrayUtils.combine(md5_16_byte, remainByte);
        }
        return md5_16_byte;
    }

    public byte[] getConfigFleWithoutChecSum() {
        ByteBuf buf = Unpooled.buffer();
        buf.writeIntLE(MAGIC_NUM);
        buf.writeIntLE((int) getFileLengthWithOutExtend());
        buf.writeBytes(HEARD_FILE_ID);
        buf.writeBytes(CONFIG_FILE_ID);
        buf.writeShortLE(getServerLength());
        buf.writeShortLE(this.reportInterval);
        buf.writeShortLE(this.baseReportInterval);
        buf.writeShortLE(this.httpPort);
        buf.writeShortLE(this.mqttPort);
        buf.writeShortLE(getDomainLength());
        buf.writeBytes(this.domain.getBytes());
        if (EXTEND) {
            buf.writeBytes(CONFIG_BASIC_ID);
            buf.writeShortLE(BASIC_LENGTH);
            buf.writeShortLE(canCollectionInterval);
            buf.writeShortLE(freCollectionInterval);
            if (freCollectionSwitch) {
                buf.writeBytes(FRESWITCH_ON);
            } else {
                buf.writeBytes(FRESWITCH_OFF);
            }

            buf.writeBytes(CONFIG_Object_ID);
            buf.writeShortLE(getObjectLength());
            for (CollectionObject obj : objs) {
                buf.writeIntLE((int) obj.getCanId());
                buf.writeShortLE(obj.getCollectionTime());
                buf.writeBytes(REMAIN_BYTE);
            }
        }
        buf.writeBytes(CONFIG_can_ID);
        buf.writeShortLE(CAN_COLLECTION_LENGTH);
        buf.writeShortLE(generalInterval);
        buf.writeShortLE(generalPreInterval);
        buf.writeShortLE(debugInterval);
        buf.writeShortLE(detailInterval);
        buf.writeShortLE(extendInterval);

        fillValue(buf, generalIntervalSwitch);
        fillValue(buf, detailIntervalSwitch);
        fillValue(buf, debugIntervalSwitch);
        fillValue(buf, extendIntervalSwitch);
        fillValue(buf, preIntervalSwitch);

        byte[] fileBytes = new byte[buf.readableBytes()];
        buf.readBytes(fileBytes);
        return fileBytes;
    }

    private void fillValue(ByteBuf buf, boolean witch) {
        if (witch) {
            buf.writeShortLE(1);
        } else {
            buf.writeShortLE(0);
        }
    }

    public int getServerLength() {
        int serverLength = BASIC_SERVER_LENGTH;
        if (domain != null) {
            serverLength += domain.getBytes().length;
        }
        return serverLength;
    }

    public int getDomainLength() {
        if (domain != null) {
            return domain.getBytes().length;
        }
        return 0;
    }

    public int getObjectLength() {
        int length = COLLECTION_LENGTH;
        if (objs != null && objs.size() > 0) {
            length = objs.size() * CollectionObject.COLLECTION_OBJ;
        }
        return length;
    }
}
