package com.fanhq.example;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import delight.nashornsandbox.NashornSandbox;
import delight.nashornsandbox.NashornSandboxes;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Hello world!
 */
public class Application {

    //private static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static ZoneOffset ZONE_OFFSET_EIGHT = ZoneOffset.ofHours(8);

    //  private final ThreadLocal<NashornSandbox> threadLocal = ThreadLocal.withInitial(this::createNashornSandbox);

    public static void main(String[] args) throws Exception {
        String formatDate = DateFormat.getDateInstance().format(new Date());
        System.out.println("当前系统时间="+formatDate);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime start = formatter.parseDateTime("2019-11-11");
        DateTime end = formatter.parseDateTime(formatDate);
        System.out.println("开始时间="+start);
        System.out.println("结束时间="+end);
        //end-start 结果可为负数、正数、0
        int months = Months.monthsBetween(start, end).getMonths();
        //取绝对值
        System.out.println("结束时间-开始时间="+Math.abs(months)+"(月)");
        System.out.println(months);

//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Base64.getDecoder().decode("MTM4MTc2Mjg4NDgK"));
//        System.out.println(UUID.randomUUID().toString());
//
//        // ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
//        //NashornScriptEngine engine = (NashornScriptEngine) scriptEngineManager.getEngineByName("nashorn");
//        String js1 = "function welcom(name){print('hello world'); return  new Date().getTime()}";
//        String js2 = "function byteToString(arr) {\n" +
//                "    if(typeof arr === 'string') {  \n" +
//                "        return arr;  \n" +
//                "    }  \n" +
//                "    arr[0]= arr[0]<<16\n" +
//                "    arr[1]=arr[0]<<8\n" +
//                "    return arr[0] + arr[1]+ arr[2];\n" +
//                "} ";
//        int processors = Runtime.getRuntime().availableProcessors();
//        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("nashorn-pool-%d").build();
//        ExecutorService executorService = new ThreadPoolExecutor(processors, 2 * processors,
//                30, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000), threadFactory);
//        NashornSandbox sandbox = NashornSandboxes.create();
//        //NativeArrayBuffer.
//        sandbox.setExecutor(executorService);
//        for (int i = 0; i < 1000; i++) {
//            long start = System.currentTimeMillis();
//            if (i % 2 == 0) {
//                sandbox.eval(js2);
//            } else {
//                sandbox.eval(js1);
//            }
//            System.out.println(System.currentTimeMillis() - start);
//        }
//        Application application = new Application();
//        NashornSandbox sandbox = application.threadLocal.get();
//        long start = System.currentTimeMillis();
//        sandbox.eval(js);
//        Object result = sandbox.getSandboxedInvocable().invokeFunction("welcom", "Nashorn" );
//        System.out.println(result);
//        System.out.println(System.currentTimeMillis() - start);
//        String a = "3_0_0";
//        byte[] bytes = a.getBytes();
//        System.out.println(bytes);
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
}

