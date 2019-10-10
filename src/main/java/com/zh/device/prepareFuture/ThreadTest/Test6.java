package com.zh.device.prepareFuture.ThreadTest;

import java.util.PriorityQueue;

public class Test6 {
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);

    public static void main(String[] args) {
        Test6 test6 = new Test6();
        Producer producer = test6.new Producer();
        Consumer consumer = test6.new Consumer();
        producer.start();
        consumer.start();
    }

    class Consumer extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (queue){
                    while (queue.size() == 0){
                        try {
                            System.out.println("队列为空，等待生产者生产数据");
                            queue.wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.poll();//每次移走队首元素
                    queue.notify();
                    System.out.println("队列内还有"+queue.size()+"元素");
                }
            }
        }
    }
    class Producer extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (queue){
                    while (queue.size() == queueSize){
                        try {
                            System.out.println("队列空间满了,等待消费者消费");
                            queue.wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.offer(1);
                    queue.notify();
                    System.out.println("向队列中插入一个元素，队列空间还有"+(queueSize-queue.size()));
                }
                
            }
        }
    }
}

