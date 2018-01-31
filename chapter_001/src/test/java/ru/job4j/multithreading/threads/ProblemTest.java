package ru.job4j.multithreading.threads;

import org.junit.Assert;
import org.junit.Test;

public class ProblemTest {

    int THREADS_QUANTITY = 100000;

    @Test
    public void checkRaceCondition() {
        final Problem problem = new Problem();

        for (int i = 0; i < THREADS_QUANTITY; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    problem.incrementCount();
                }
            }).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Expected counter: " + THREADS_QUANTITY);
        System.out.println("Unsafe counter: " + problem.getCount());
        System.out.println("Volatile counter: " + problem.getVolatileCounter());
        System.out.println("Atomic counter: " + problem.getAtomicCounter());
        Assert.assertNotEquals(THREADS_QUANTITY, problem.getCount());
        Assert.assertNotEquals(THREADS_QUANTITY, problem.getVolatileCounter());
        Assert.assertEquals(THREADS_QUANTITY, problem.getAtomicCounter());
    }

    @Test
    public void checkDeadlock() {
        final Problem problem1 = new Problem();
        final Problem problem2 = new Problem();
        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                problem1.bar(problem2);
            }
        }, "First");
        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                problem2.bar(problem1);
            }
        }, "Second");
        first.start();
        second.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(first.getState().toString(), "BLOCKED");
        Assert.assertEquals(second.getState().toString(), "BLOCKED");
    }
}
