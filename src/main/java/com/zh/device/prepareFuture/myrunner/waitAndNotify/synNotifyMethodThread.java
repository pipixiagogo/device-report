package com.zh.device.prepareFuture.myrunner.waitAndNotify;

public class synNotifyMethodThread extends Thread {
    private Object lock;

    public synNotifyMethodThread(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service = new Service();
        service.synNotifyMethod(lock);
    }
}
