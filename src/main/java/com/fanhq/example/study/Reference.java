package com.fanhq.example.study;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author fanhaiqiu
 * @date 2020/3/4
 */
public class Reference {

    public static void main(String[] args) {
        //软引用 jvm内存够用的时候，内存不会被回收
        String data1 = new String("123");
        SoftReference<String> softReference = new SoftReference<>(data1);
        data1 = null;
        System.gc();
        System.out.println(softReference.get());

        //弱引用 jvm直接回收内存
        String data2 = new String("123");
        WeakReference<String> weakReference = new WeakReference<>(data2);
        data2 = null;
        System.gc();
        System.out.println(weakReference.get());
    }
}
