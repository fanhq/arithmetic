package com.fanhq.example;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import delight.nashornsandbox.NashornSandbox;
import delight.nashornsandbox.NashornSandboxes;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import javax.sound.midi.Soundbank;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.*;

/**
 * Hello world!
 */
public class Application {

    //private static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static ZoneOffset ZONE_OFFSET_EIGHT = ZoneOffset.ofHours(8);

    //  private final ThreadLocal<NashornSandbox> threadLocal = ThreadLocal.withInitial(this::createNashornSandbox);

    public static void main(String[] args) throws Exception {
        String a= "设备号:15,起始频率:1550,结束频率:1620,功放:关闭,温度:26.7,正向功率:-1.22,反向功率:2.1,电流:0,电压:27.25,驻波比:1.2|设备号:24,起始频率:2395,结束频率:2495,功放:关闭,温度:31.7,正向功率:-16.5,反向功率:-2.05,电流:0.18,电压:27.54,驻波比:1.2|设备号:58,起始频率:5715,结束频率:5850,功放:关闭,温度:28.6,正向功率:-2.34,反向功率:-2.54,电流:0.25,电压:27.3,驻波比:1.2";
        String[] split = a.split("\\|");
        System.out.println(split.length);
        //System.out.println(11>11);
        //int sum = 0;
        //int time = 11;
        //for (int i = 1; i< time; i++){
        //    int skipCycle4NextSend = getSkipCycle4NextSend(i);
        //    sum = sum +skipCycle4NextSend;
        //}
        //System.out.println(getSkipCycle4NextSend(10));
        //System.out.println(sum);
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

