package com.zh.device.prepareFuture.runner.myrunner;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {

    private String thredName;

    public MyCallable(String thredName){
        this.thredName=thredName;
    }
    @Override
    public Object call() throws Exception {
        return thredName;
    }
}
