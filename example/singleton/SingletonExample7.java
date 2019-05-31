package com.demo.concurrency.example.singleton;

import com.demo.concurrency.annoations.ReCommend;
import com.demo.concurrency.annoations.ThreadSafe;

import java.lang.ref.PhantomReference;

/**
 * 枚举模式 最安全
 */
@ThreadSafe
@ReCommend
public class SingletonExample7 {

    private SingletonExample7(){
    }

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }



    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        //JVM保证这个方法绝对只调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }


    }

}
