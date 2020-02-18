package com.fanhq.example.problem;

/**
 * @author fanhaiqiu
 * @date 2020/1/15
 */
public class SingletonObject {

    private enum Singleton {
        INSTANCE;
        private final SingletonObject instance;

        Singleton() {
            instance = new SingletonObject();
        }

        private SingletonObject getInstance() {
            return instance;
        }
    }
    public static SingletonObject getInstance() {

        return Singleton.INSTANCE.getInstance();
    }
}
