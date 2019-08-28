package com.zh.device.model.shiPeiZheModel;

public class Test {
    public static void main(String[] args) {
        SocketCreate socketCreate = new ThreeSocket();
        socketCreate.insert();

        SocketCreate change = new AdapetChange();
        change.insert();
    }
}
