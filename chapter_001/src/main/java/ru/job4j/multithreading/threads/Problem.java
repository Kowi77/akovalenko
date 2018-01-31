package ru.job4j.multithreading.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class Problem {

    private int count;
    private volatile int volatileCounter;
    private AtomicInteger atomicCounter = new AtomicInteger(0);

    public void incrementCount() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        volatileCounter++;
        atomicCounter.incrementAndGet();
    }

    public int getCount() {
        return count;
    }

    public int getVolatileCounter() {
        return volatileCounter;
    }

    public int getAtomicCounter() {
        return atomicCounter.get();
    }

    public synchronized void foo(Problem problem) {
        System.out.println(Thread.currentThread().getName() + " in foo");
    }

    public synchronized void bar(Problem problem) {
        System.out.println(Thread.currentThread().getName() + " in bar");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        problem.foo(this);
    }
}
