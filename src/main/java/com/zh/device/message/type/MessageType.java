package com.zh.device.message.type;


import java.util.HashMap;
import java.util.Map;

public enum MessageType {
    LIVE((byte)0x91),
    RDBMSG((byte)0x81),
    UNKNOW(),
    BREAKDOWN((byte)0X82),
    RDBUPGRADE((byte)0x42),
    BMUUPGRADE((byte)0x43),
    CONFIG((byte)0xEF),
    CONFIG_FILE((byte)0x41),
    SYNC_TIME((byte)0X44),
    RESTART((byte) 0X45);
    private static Map<Byte, MessageType> map;
    MessageType(byte... bytes){
        init( bytes );
    }

    private void init(byte[] bytes) {
        if( map == null )
            map = new HashMap<>();
        if( bytes != null ){
            for( byte b : bytes ){
                Byte bt = Byte.valueOf( b );
                map.put( bt,this );
            }
        }
    }

    public static MessageType valueOf(byte b){
        MessageType type = map.get(b);
        if( type == null )
            return MessageType.UNKNOW;
        return type;
    }
}
