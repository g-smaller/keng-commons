package com.keng.common.thread;

public class SynchronizedTest {


    public static void main(String[] args) {
        Counter counter = new Counter();
        new Thread(() -> {
            counter.set(Thread.currentThread().getName(), "000000");
            counter.print();
            Counter.print1();
            Counter.print2();
        }).start();

        new Thread(() -> {
            counter.set(Thread.currentThread().getName(), "111111");
            counter.print();
            Counter.print1();
            Counter.print2();
        }).start();
    }

}

class Counter {
    String username;
    String password;

    public synchronized void set(String username, String password) {
        this.username = username;
        this.password = password;
        print();
    }

    public void print() {
        synchronized (Counter.class) {
            for (int i = 0; i < 5; i++) {
                System.out.println(String.format("{\"username\": %s - %s, \"password\": %s - %d}", username, Thread.currentThread().getName(), password, i));
            }
        }
    }

    public synchronized static void print1() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i + " - print1 - " + Thread.currentThread().getName());
        }
    }

    public synchronized static void print2() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i + " - print2 - " + Thread.currentThread().getName());
        }
    }

}
