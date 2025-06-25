package com.wtp.base.common.collection.map;

import java.util.HashMap;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();

        map.put(1, 1);

        Object o = map.get(1);
    }
}
