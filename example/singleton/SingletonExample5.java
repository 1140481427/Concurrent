package com.demo.concurrency.example.singleton;


import com.demo.concurrency.annoations.ThreadSafe;

@ThreadSafe
public class SingletonExample5 {

    private SingletonExample5(){

    }

    private volatile static SingletonExample5 instence = null;

    //volatile + 双重检测机制 -》 会限制指令重排

    // 1、 memory = allocate() 分配对象的内存空间
    // 2、 ctorInstance() 初始化对象
    // 3、 instance = memory 设置instance指向刚分配的内存


    /**
     * 因为指令重排 导致线程不安全
     * @return
     */
    public static SingletonExample5 getInstence(){
        if(instence == null) { //双重检测机制
            synchronized (SingletonExample5.class){ //同步锁
                if (instence == null) {
                    instence = new SingletonExample5();
                }
            }
        }
        return instence;
    }


}
