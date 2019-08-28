package com.zh.device.model.zeRenLianModel;

public class Test {
    public static void main(String[] args) {
        Handler handler1=new ClentHandler1();
        Handler handler2 = new ClentHandler2();
        Handler handler3 = new ClentHandler3();
        handler1.setHandler(handler2);
        handler2.setHandler(handler3);
        for(int i=0;i<35;i++){
            handler1.handlerRequest(i);
        }
    }
}
