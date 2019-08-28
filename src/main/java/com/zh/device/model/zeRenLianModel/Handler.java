package com.zh.device.model.zeRenLianModel;


public abstract class Handler {
    protected Handler handler;

    public void setHandler(Handler handler){
        this.handler=handler;
    }

    public abstract void handlerRequest(int request);
}
