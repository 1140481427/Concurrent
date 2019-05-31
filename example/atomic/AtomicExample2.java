package com.demo.concurrency.example.atomic;


import com.demo.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@ThreadSafe
public class AtomicExample2 {

    public static AtomicLong count = new AtomicLong(0);

    //请求总数
    public static int clientTotal = 5000;

    //同时并发线程数
    public static int threadTotal = 200;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量 同时可执行200
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);//计数器闭锁
        for (int i=0; i<clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    //
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();//每执行一个  计数器减一
            });
        }
        //等计数器为0之后 才唤醒此操作
        countDownLatch.await();
        executorService.shutdown();
        log.info("count={}", count.get());

    }

    private static void add(){
        count.getAndIncrement();
        //count.getAndAccumulate();
    }


}
