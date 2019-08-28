package com.zh.device.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Const {
    //配置文件应答
    public static final short NEET_NEXT = 0;
    public static final short FINISH_SUCCESS = 1;
    public static final short FINISH_ERROR = 2;

    //固件类型
    public static final String RDB = "RDB";
    public static final String BMU = "BMU";
    public static final String RDB_SOFT_PACK_MAGIC_NUM = "PKGS";
    public static final String VERSION_SPLIT = "#";

    public static final int PARAMS_ILLEGAL = 5;//参数非法
    public static final String PARAM_ILL = "参数错误";
    public static final String QUERY_SUCS = "查询成功";
    public static final String S2D_PREFIX = "$RDB/";
    public static final String S2D_SUFFIX = "/MSG/D2S";

    public static final byte[] DEFAULT_BID = new byte[]{0x00,0x00,0x00,0x00};
    public static final byte DEFAULT_VERSION = 0x00;
    public static final byte CONFIG_SEND_AID=0x41;
    public static final byte CONFIG_SEND_MID=0x01;
    public static final String DOMAIN_ACCESS = "1";
    public static final String DOMAIN_DENY = "0";

    public static final Integer DESC = -1;
    public static final Integer ASC = 1;
    public static final Map<String,Set<String>> SORT_FIELDS=new HashMap<>();
    public static final String OPERATIONLOG_TABLE = "OPERATIONLOG_TABLE";
    public static final String CLEANFLUSHLOG_TABLE = "CLEANFLUSHLOG_TABLE";
    public static final String RESTARTLOG_TABLE = "RESTARTLOG_TABLE";




    public static final String getSendTopic(String deviceName){
        return S2D_PREFIX+deviceName+S2D_SUFFIX;
    }
    public static final String getD2SSendTopic(String deviceName){
        return S2D_PREFIX+deviceName+S2D_SUFFIX;
    }
}
