package com.fanhq.example.problem;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author fanhaiqiu
 * @date 2020/1/17
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private int capacity;

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() > capacity;
    }

    public LRUCache(int capacity) {
        super(16, 0.75f, true);
        this.capacity = capacity;
    }
}
