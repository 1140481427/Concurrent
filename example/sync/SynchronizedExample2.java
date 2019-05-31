package com.demo.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample2 {

    public static void test1(){
        synchronized(SynchronizedExample2.class) {
            for(int i=0; i<10; i++) {
                log.info("test1 - {}", i);
            }
        }
    }

    public static synchronized void test2(){
        for(int i=0; i<10; i++) {
            log.info("test2 - {}", i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 synchronizedExample1 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() -> {
            synchronizedExample1.test1();
        });

        executorService.execute(() -> {
            synchronizedExample1.test1();
        });
    }
}
