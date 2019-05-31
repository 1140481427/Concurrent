package com.demo.concurrency.example.atomic;


import com.demo.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    @Getter
    public volatile int count = 100;

    private static AtomicExample5 atomicExample5 = new AtomicExample5();

    public static void main(String[] args){
        if(updater.compareAndSet(atomicExample5,100,120)){
            log.info("update sucess! 1 {}",atomicExample5.getCount());
        }

        if(updater.compareAndSet(atomicExample5,100,120)){
            log.info("update sucess! 2 {}",atomicExample5.getCount());
        } else {
            log.info("update failed! {}",atomicExample5.getCount());
        }
    }
}
