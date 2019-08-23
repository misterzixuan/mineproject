package com.threadlocalstudy;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {
    private static final AtomicInteger nextId = new AtomicInteger(0);

               // Thread local variable containing each thread's IDprivate static final ThreadLocal<Integer> threadId =
               private static final ThreadLocal<Integer> threadId =new ThreadLocal<Integer>() {
                      @Override protected Integer initialValue() {
                              return nextId.getAndIncrement();
                      }
              };

    public static int get() {
                  return threadId.get();
              }

    public static void main(String[] args) {

        System.out.println(get());
        System.out.println(get());

    }
}
