package com.zh.device.model.zeRenLianModel;



public class ClentHandler2 extends Handler {
    @Override
    public void handlerRequest(int request) {
        if(request >10 && request<20){
            System.out.println("ClentHandler2 处理:"+request+"任务");
        }else {
            handler.handlerRequest(request);
        }
    }
}
