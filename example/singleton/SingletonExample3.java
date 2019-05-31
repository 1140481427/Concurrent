package com.demo.concurrency.example.singleton;

import com.demo.concurrency.annoations.ThreadSafe;

@ThreadSafe
public class SingletonExample3 {

    private SingletonExample3(){

    }

    private static SingletonExample3 instence = null;

    /**
     * 线程安全 但是性能低
     * @return
     */
    public static synchronized SingletonExample3 getInstence(){
        if(instence == null) {
            instence = new SingletonExample3();
        }
        return instence;
    }


}
