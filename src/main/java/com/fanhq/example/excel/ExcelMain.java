package com.fanhq.example.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanhaiqiu
 * @date 2020/7/30
 */
public class ExcelMain {

    public static void main(String[] args) {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
//        String fileName1 = "D:\\file\\excel\\2-9台账.xls";
//        Map<String, String> data = new HashMap<>();
//        List<DemoDTO1> list1 = new ArrayList<>();
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName1, DemoDTO1.class, new DemoDataListener1(list1)).sheet().doRead();
//        Map<String, DemoDTO1> map = new HashMap<>();
//        list1.forEach(x->{
//            map.put(x.getRoomName(), x);
//        });

        String fileName2 = "D:\\file\\excel\\2020年集美嘉悦许冲收费台账.xls";
        List<DemoDTO2> list2 = new ArrayList<>();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName2, DemoDTO2.class, new DemoDataListener2(list2)).sheet().headRowNumber(2).doRead();
        System.out.println(list2.size());
    }
}
