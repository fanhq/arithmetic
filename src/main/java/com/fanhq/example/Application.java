package com.fanhq.example;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import delight.nashornsandbox.NashornSandbox;
import delight.nashornsandbox.NashornSandboxes;

import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.*;

/**
 * Hello world!
 */
public class Application {

    //private static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static ZoneOffset ZONE_OFFSET_EIGHT = ZoneOffset.ofHours(8);

    //  private final ThreadLocal<NashornSandbox> threadLocal = ThreadLocal.withInitial(this::createNashornSandbox);

    public static void main(String[] args) throws Exception {
        String[] userTypes = new String[2];
        userTypes[0] = "1";
        userTypes[1]= "2";
        System.out.println(Arrays.asList(userTypes).contains("1"));
    }

    public static String byteToHex(byte b) {
        String hex = Integer.toHexString(b & 0xFF);
        if (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
    }

    public static int getSkipCycle4NextSend(int sentTimes) {
        int sum = 0;
        int defaultSum = 1596;
        //程序中由于多线程的的交替执行，可能会导致sentTimes很大，导致下面的for循环卡死，进而不会释放MemoryMessagePipe
        //导致其它线程block
        if (sentTimes >= 15) {
            return defaultSum;
        }
        for (int i = 1; i <= sentTimes; i++) {
            sum += fibonacci(i);
        }
        return sum;
    }

    public static int fibonacci(int n) {
        if (n <= 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }


    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    private NashornSandbox createNashornSandbox() {
        int processors = Runtime.getRuntime().availableProcessors();
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("nashorn-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(processors, 2 * processors,
                30, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000), threadFactory);
        NashornSandbox sandbox = NashornSandboxes.create();
        sandbox.setExecutor(executorService);
        return sandbox;
    }

    /**
     * 获取两个日期相差的月数
     */
    public static int getMonthDiff(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        if (year1 == year2) {
            return month2 - month1;
        }
        int yearInterval = year2 - year1;
        return ((month2 + 12) - month1) + ((yearInterval - 1) * 12);
    }
}

