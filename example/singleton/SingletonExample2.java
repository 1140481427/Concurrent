package com.demo.concurrency.example.singleton;

import com.demo.concurrency.annoations.ThreadSafe;

@ThreadSafe
public class SingletonExample2 {

    private SingletonExample2(){

    }

    private static SingletonExample2 instence = new SingletonExample2();

    public static SingletonExample2 getInstence(){
        return instence;
    }


}
