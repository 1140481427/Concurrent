package com.demo.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SynchronizedExample1 {

    public void test1(){
        synchronized(this) {
            for(int i=0; i<10; i++) {
                log.info("test1 - {}", i);
            }
        }
    }

    public synchronized void test2(){
        for(int i=0; i<10; i++) {
            log.info("test2 - {}", i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() -> {
            synchronizedExample1.test1();
        });

        executorService.execute(() -> {
            synchronizedExample1.test1();
        });
    }
}
