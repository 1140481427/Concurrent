package com.demo.concurrency.example.immutable;

import com.demo.concurrency.annoations.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
@ThreadSafe
public class immutableDemo2 {

    private  static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,1);
        map.put(3,4);
        map.put(5,6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1,4);   //会抛异常
        log.info("{}",map.get(1));
    }
}
