package com.fanhq.example.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.merge.OnceAbsoluteMergeStrategy;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author fanhaiqiu
 * @date 2020/7/30
 */
public class ExcelMain {

    public static void main(String[] args) {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "D:\\file\\excel\\text2.xlsx";
        List<String> a = Lists.newArrayList("A");
        List<String> b = Lists.newArrayList("B");
        List<String> c = Lists.newArrayList("C");
        List<List<String>> head = new ArrayList<>();
        head.add(a);
        head.add(b);
        head.add(c);
        List<List<String>> data = new ArrayList<>();
        List<String> v1 = Lists.newArrayList("1", "2", "3");
        List<String> v2 = Lists.newArrayList("1", "5", "9");
        List<String> v3 = Lists.newArrayList("1", "8", "9");
        data.add(v1);
        data.add(v2);
        data.add(v3);
        OnceAbsoluteMergeStrategy onceAbsoluteMergeStrategy1 =new OnceAbsoluteMergeStrategy(1,3,0,0);
        OnceAbsoluteMergeStrategy onceAbsoluteMergeStrategy2 =new OnceAbsoluteMergeStrategy(1,1,0,0);
        EasyExcel.write(fileName)
                // 这里放入动态头
                .head(head).sheet("模板")
                .registerWriteHandler(onceAbsoluteMergeStrategy1)
                //.registerWriteHandler(onceAbsoluteMergeStrategy2)
                // 当然这里数据也可以用 List<List<String>> 去传入
                .doWrite(data);
    }
}
