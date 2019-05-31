package com.demo.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class ForkJoinTaskDemo extends RecursiveTask<Integer> {

    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinTaskDemo(int start,int end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        int sum = 0;
        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= threshold;
        if(canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            //如果任务大于阈值 就分裂成俩个子任务计算
            int middle =  (start + end) / 2;
            ForkJoinTaskDemo leftTask = new ForkJoinTaskDemo(start, middle);
            ForkJoinTaskDemo rightTask = new ForkJoinTaskDemo(middle + 1, end);

            //执行子任务
            leftTask.fork();
            rightTask.fork();

            //等待任务执行结束合并其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //生成一个计算任务  1+2+3+4
        ForkJoinTaskDemo task = new ForkJoinTaskDemo(99,9999);

        //执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);

        try {
            log.info("result:{}",result.get());
        } catch (Exception e) {
            log.error("exception",e);
        }
    }
}
