package com.fanhq.example.excel;

import com.alibaba.excel.EasyExcel;

import java.util.*;

/**
 * @author fanhaiqiu
 * @date 2020/7/30
 */
public class ExcelMain {

    public static void main(String[] args) {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName1 = "D:\\file\\excel\\2-9栋欠费明细表.xlsx";
        List<DemoDTO1> list1 = new ArrayList<>();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName1, DemoDTO1.class, new DemoDataListener1(list1)).sheet().headRowNumber(3).doRead();
        List<DemoDTO4> list4 = new ArrayList<>();
        list1.forEach(x -> {
            DemoDTO4 demoDTO4 = new DemoDTO4();
            demoDTO4.setName(x.getName());
            list4.add(demoDTO4);
        });

        String fileName2 = "D:\\file\\excel\\明细2-9栋已装修.xlsx";
        List<DemoDTO2> list2 = new ArrayList<>();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName2, DemoDTO2.class, new DemoDataListener2(list2)).sheet().headRowNumber(1).doRead();
        list2.forEach(x -> {
            list4.forEach(y -> {
                if (y.getName() != null && y.getName().equals(x.getName()) && "是".equals(x.getStatus())) {
                    y.setStats("已装修");
                }
            });
        });

        String fileName3 = "D:\\file\\excel\\明细2-9栋已入住.xlsx";
        List<DemoDTO3> list3 = new ArrayList<>();
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName3, DemoDTO3.class, new DemoDataListener3(list3)).sheet().headRowNumber(1).doRead();
        list3.forEach(x -> {
            list4.forEach(y -> {
                if (y.getName() != null && y.getName().equals(x.getName()) && "是".equals(x.getStatus())) {
                    y.setStats("已入住");
                }
            });
        });
        list4.forEach(x -> {
            System.out.println(x.getName() + x.getStats());
        });
        System.out.println("end");
//        String fileName2 = "D:\\file\\excel\\2020年集美嘉悦许冲收费台账.xls";
//        List<DemoDTO2> list2 = new ArrayList<>();
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName2, DemoDTO2.class, new DemoDataListener2(list2)).sheet().headRowNumber(2).doRead();
//        list2.forEach(x -> {
//            DemoDTO1 demoDTO1 = map.get(x.getRoom());
//            if (demoDTO1.getTokeRoom().equals("否")) {
//                System.out.println("未接房");
//            } else if (demoDTO1.getTokeRoom().equals("是") && demoDTO1.getTokeDo().equals("否")) {
//                System.out.println("已接房未装修");
//            } else {
//                System.out.println("已接房已装修");
//            }
//        });
    }
}
