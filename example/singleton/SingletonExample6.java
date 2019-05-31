package com.demo.concurrency.example.singleton;

import com.demo.concurrency.annoations.ThreadSafe;

@ThreadSafe
public class SingletonExample6 {

    private SingletonExample6(){

    }

    private static SingletonExample6 instence = null;

    static {
        instence =  new SingletonExample6();
    }

    public static SingletonExample6 getInstence(){
        return instence;
    }


}
