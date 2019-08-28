package com.zh.device.message;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

public class OnOffMessage extends JMessage {
    private static final String clientid = "clientid";
    private static final String username = "username";
    private static final String ipaddress = "ipaddress";
    private static final String clean_sess = "clean_sess";
    private static final String protocol = "protocol";
    private static final String connack = "connack";
    private static final String ts = "ts";
    private static final String reason = "reason";
    private JSONObject json;
    private boolean online;

    private void verify(){
        if( this.json == null || this.json.size() == 0 ){
            throw new RuntimeException("数据接收不完整");
        }
    }

    public String getClientid() {
        try {
            verify();
            return json.getString(clientid);
        }catch (Exception e){
            return null;
        }
    }

    public String getUsername() {
        try {
            verify();
            return json.getString(username);
        }catch (Exception e){
            return null;
        }
    }

    public String getIpaddress() {
        try {
            verify();
            return json.getString(ipaddress);
        }catch (Exception e){
            return null;
        }
    }

    public Boolean getClean_sess() {
        try {
            verify();
            return json.getBoolean(clean_sess);
        }catch (Exception e){
            return null;
        }
    }

    public String getProtocol() {
        try {
            verify();
            return json.getString(protocol);
        }catch (Exception e){
            return null;
        }
    }

    public String getConnack() {
        try {
            verify();
            return json.getString(connack);
        }catch (Exception e){
            return null;
        }
    }

    public Long getTs() {
        try {
            verify();
            return json.getLong(ts);
        }catch (Exception e){
            return null;
        }
    }

    public String getReason() {
        try {
            verify();
            return getNormalReason( json.getString(reason) );
        }catch (Exception e){
            return null;
        }
    }

    private String getNormalReason(String string) {
        if(StringUtils.isEmpty( string )){
            return "正常下线.";
        }else {
            switch (string){
                case "normal":
                    return "正常下线";
                case "closed":
                    return "关闭";
                case "keepalive_timeout":
                    return "连接超时";
                default:
                    return "正常下线";
            }
        }
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public void setJson(String json) {
        this.json = JSONObject.parseObject( json );
    }

    public JSONObject getJson() {
        return json;
    }

    public boolean isOnline() {
        return online;
    }
}
