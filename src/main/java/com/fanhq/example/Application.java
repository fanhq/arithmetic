package com.fanhq.example;

import com.fanhq.example.problem.LRUCache;

/**
 * Hello world!
 */
public class Application {

    public static void main(String[] args) throws Exception {
        LRUCache<String, String> cache = new LRUCache<>(5);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.put("4", "4");
        cache.put("5", "5");
        cache.get("1");
        cache.put("6", "6");
        cache.put("7", "7");
        cache.forEach((k, v) -> {
            System.out.println(v);
        });
    }
}


