package com.demo.concurrency.example.singleton;

import com.demo.concurrency.annoations.NotThreadSafe;

@NotThreadSafe
public class SingletonExample1 {

    private SingletonExample1(){

    }

    private static SingletonExample1 instence = null;

    public static SingletonExample1 getInstence(){
        if(instence == null) {
            instence = new SingletonExample1();
        }
        return instence;
    }


}
