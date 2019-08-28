package com.zh.device.model.zeRenLianModel;

public class ClentHandler3 extends Handler {
    @Override
    public void handlerRequest(int request) {
        if(request > 20 && request <30){
            System.out.println("ClentHandler3处理:"+request+"任务");
        }else {
            System.out.println("没人管的任务:"+request);
        }
    }
}
