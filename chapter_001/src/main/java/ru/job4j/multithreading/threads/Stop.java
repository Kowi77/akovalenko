package ru.job4j.multithreading.threads;

import java.util.Date;

public class Stop {

    private static final String TEXT = " This this very long phrase has 44 chars and    16 spaces ! ";
    private static final long RUNTIME_MS = 3000L;
    private static int count;
    private static final Thread mainThread;

    static {
        System.out.println("START OF PROGRAM!");
        mainThread = new Thread(new CountChar());
    }

    public static void main(String[] args) {
        mainThread.start();
        new Thread(new Time()).start();
    }

    public static class Time implements Runnable {

        @Override
        public void run() {
            long startTime = new Date().getTime();
            while ((new Date().getTime() - startTime) < RUNTIME_MS) {
            }
            mainThread.interrupt();
        }
    }

    public static class CountChar implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < TEXT.length(); i++) {
                if (TEXT.charAt(i) != ' ') {
                    count++;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("PROGRAM IS TERMINATED AFTER " + RUNTIME_MS + "ms WITH " + count + " CHAR");
                    return;
                }
            }
        }
    }
}
