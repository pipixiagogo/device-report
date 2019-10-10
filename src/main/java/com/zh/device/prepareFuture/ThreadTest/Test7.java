package com.zh.device.prepareFuture.ThreadTest;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test7 {
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Test7 test7 = new Test7();
        Consumer consumer = test7.new Consumer();
        Producer producer = test7.new Producer();
        consumer.start();
        producer.start();
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("队列为空，等待生产者生产数据");
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            condition.signal();
                        }
                    }
                    queue.poll();//每次移走队首元素
                    condition.signal();
                    System.out.println("队列内还有" + queue.size() + "元素");
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == queueSize) {
                        try {
                            System.out.println("队列空间满了,等待消费者消费");
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            condition.signal();
                        }
                    }
                    queue.offer(1);
                    condition.signal();
                    System.out.println("向队列中插入一个元素，队列空间还有" + (queueSize - queue.size()));
                } finally {
                    lock.unlock();
                }

            }
        }
    }
}
