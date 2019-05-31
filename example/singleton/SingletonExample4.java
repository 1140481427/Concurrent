package com.demo.concurrency.example.singleton;

import com.demo.concurrency.annoations.NotThreadSafe;
import com.demo.concurrency.annoations.ThreadSafe;

@NotThreadSafe
public class SingletonExample4 {

    private SingletonExample4(){

    }

    private static SingletonExample4 instence = null;


    // 1、 memory = allocate() 分配对象的内存空间
    // 2、 ctorInstance() 初始化对象
    // 3、 instance = memory 设置instance指向刚分配的内存

    //  JVM和cpu优化，发生了指令重排

    // 1、 memory = allocate() 分配对象的内存空间
    // 3、 instance = memory 设置instance指向刚分配的内存
    // 2、 ctorInstance() 初始化对象

    /**
     * 因为指令重排 导致线程不安全
     * @return
     */
    public static SingletonExample4 getInstence(){
        if(instence == null) { //双重检测机制
            synchronized (SingletonExample4.class){ //同步锁
                if (instence == null) {
                    instence = new SingletonExample4();
                }
            }
        }
        return instence;
    }


}
