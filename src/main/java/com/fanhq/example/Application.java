package com.fanhq.example;

import com.fanhq.example.excel.DemoDTO1;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Hello world!
 */
public class Application {

    private static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static ZoneOffset ZONE_OFFSET_EIGHT = ZoneOffset.ofHours(8);

    public static void main(String[] args) throws Exception {
        DemoDTO1 d = new DemoDTO1();
        test(d);
        System.out.println(d.getRoomName());
    }

    private static void test(DemoDTO1 demoDTO1){
        demoDTO1.setRoomName("dddd");
    }
}


