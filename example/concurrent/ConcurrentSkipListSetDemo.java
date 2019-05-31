package com.demo.concurrency.example.concurrent;

import com.demo.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class ConcurrentSkipListSetDemo {

    //请求总数
    public static int clientTotal = 5000;

    //同时并发线程数
    public static int threadTotal = 200;

    private static Set<Integer> set = new ConcurrentSkipListSet<>();


    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量 同时可执行200
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);//计数器闭锁
        for (int i=0; i<clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    //
                    semaphore.acquire();
                    update(count);
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
        log.info("list.size =  {}", set.size());

    }

    private static void update(int i) {
        set.add(i);
    }
}
