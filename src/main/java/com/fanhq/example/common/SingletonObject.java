package com.fanhq.example.common;

/**
 * @author: fanhaiqiu
 * @date: 2021/3/19
 */
public class SingletonObject {

    private SingletonObject(){
    }

    public static SingletonObject getInstance(){
        return Holder.singletonObject;
    }

    static class Holder{
        private static SingletonObject singletonObject = new SingletonObject();
    }
}
