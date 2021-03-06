package ru.job4j.multithreading.threads;

public class SpacesCalculation {

    private static String text;

    static {
        System.out.println("START OF PROGRAM!");
        text = " This this very long phrase has 12 words and    16 spaces ! ";
    }

    public static void main(String[] args) {

        System.out.println(text);
        Thread thread1 = new Thread(new Space());
        Thread thread2 = new Thread(new Word());
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()){
        }
        System.out.println("FINISH OF PROGRAM!");
    }

    static class Space implements Runnable {
        int count;
        @Override
        public void run() {
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == ' ') {
                    System.out.println("Space # " + ++count);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Word implements Runnable {
        int count;
        boolean isNewWord = true;
        @Override
        public void run() {
              for (int i = 0; i < text.length(); i++) {
                  if (text.charAt(i) != ' ') {
                      if (isNewWord) {
                          System.out.println("Word # " + ++count);
                          isNewWord = false;
                      }
                  } else {
                      isNewWord = true;
                  }
                  try {
                      Thread.sleep(10);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
        }
    }
}
