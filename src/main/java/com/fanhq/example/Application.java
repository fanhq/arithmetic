package com.fanhq.example;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import delight.nashornsandbox.NashornSandbox;
import delight.nashornsandbox.NashornSandboxes;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * Hello world!
 */
public class Application {

    private static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static ZoneOffset ZONE_OFFSET_EIGHT = ZoneOffset.ofHours(8);

    //  private final ThreadLocal<NashornSandbox> threadLocal = ThreadLocal.withInitial(this::createNashornSandbox);

    public static void main(String[] args) throws Exception {
        // ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        //NashornScriptEngine engine = (NashornScriptEngine) scriptEngineManager.getEngineByName("nashorn");
        //String js = "function welcom(name){print('hello world'); return  new Date().getTime()}";
        String js = "function byteToString(arr) {\n" +
                "    if(typeof arr === 'string') {  \n" +
                "        return arr;  \n" +
                "    }  \n" +
                "    arr[0]= arr[0]<<16\n" +
                "    arr[1]=arr[0]<<8\n" +
                "    return arr[0] + arr[1]+ arr[2];\n" +
                "} ";
//        CompiledScript compiledScript = engine.compile(js);
//        compiledScript.eval();
//        engine.eval(js);
//        Invocable invocable = engine;
//        List<String> ar = new ArrayList<>();
//        ar.add("b");
//        ar.add("c");
//        String s = JSON.toJSONString(ar);
//        System.out.println(s);
//        Object result = invocable.invokeFunction("byteToString", "{\"method\":\"thing.property.post\",\"id\":\"1\",\"params\":{\"someArray\":{\"value\":[\"qk\",\"HJOmR\"]}},\"version\":\"1.0\"}".getBytes());
//        System.out.println(result);
        //System.out.println(bytesToHex("Nashorn".getBytes()));
        int processors = Runtime.getRuntime().availableProcessors();
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("nashorn-pool-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(processors, 2 * processors,
                30, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000), threadFactory);
        NashornSandbox sandbox = NashornSandboxes.create();
        //NativeArrayBuffer.
        sandbox.setExecutor(executorService);
//        for (int i = 0; i < 1000; i++) {
//            long start = System.currentTimeMillis();
        sandbox.eval(js);
        Object result = sandbox.getSandboxedInvocable().invokeFunction("byteToString", "我".getBytes());
        System.out.println(result);
        System.out.println(Integer.toBinaryString("我".getBytes()[0]));
        System.out.println(Integer.toBinaryString("我".getBytes()[1]));
        System.out.println(Integer.toBinaryString("我".getBytes()[2]));
//        1100010 00010001
//        11100110
//        10001000
//        10010001
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

