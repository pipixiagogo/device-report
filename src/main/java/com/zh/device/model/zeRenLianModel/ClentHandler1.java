package com.zh.device.model.zeRenLianModel;

public class ClentHandler1 extends Handler {

    
    @Override
    public void handlerRequest(int request) {
        if(request >0 && request<10){
            System.out.println("ClentHandler1处理:"+request+"任务");
        }else {
            handler.handlerRequest(request);
        }
    }
}
