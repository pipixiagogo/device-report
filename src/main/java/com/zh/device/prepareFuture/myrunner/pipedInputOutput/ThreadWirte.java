package com.zh.device.prepareFuture.myrunner.pipedInputOutput;

import java.io.PipedOutputStream;

public class ThreadWirte extends Thread {
    private WriteData write;
    private PipedOutputStream out;

    public ThreadWirte(WriteData write, PipedOutputStream out) {
        super();
        this.write = write;
        this.out = out;
    }

    @Override
    public void run() {
        write.writeMethod(out);
    }
}
