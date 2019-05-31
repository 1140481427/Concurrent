package com.demo.concurrency.example.immutable;

import com.demo.concurrency.annoations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@Slf4j
@ThreadSafe
public class immutableDemo3 {

    private static ImmutableList<Integer> list = ImmutableList.of(1,2,3,3,3);

    private static ImmutableSet<Integer> set = ImmutableSet.copyOf(list);

    private static ImmutableMap map = ImmutableMap.of(1,1,2,2);

    private static ImmutableMap map2 = ImmutableMap.<Integer,Integer>builder().put(1,1).put(2,2).put(3,3).build();

    public static void main(String[] args) {
        list.add(2);
        System.out.println(list.toString());
    }

}
