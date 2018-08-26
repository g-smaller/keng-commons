package com.keng.common;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class ConcurrencyTest {


    int threadNum = 200;
    CountDownLatch countDownLatch = new CountDownLatch(threadNum);
    @Test
    public void test() {
        for (int i = 0; i < threadNum; i++) {
            new Thread(new TestRunnable(i)).start();
            countDownLatch.countDown();
        }
    }

    class TestRunnable implements Runnable {

        private int i;
        public TestRunnable(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                System.out.println("等待 - " + i);
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i + " - " + System.currentTimeMillis());
        }
    }

}
