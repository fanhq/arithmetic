package com.fanhq.example.common;

import javax.script.*;
import java.util.Date;
import java.util.List;

/**
 * @author: fanhaiqiu
 * @date: 2021/4/13
 */
public class RunJavascript {
    public void getScriptEngineFactory() {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = manager.getEngineFactories();
        for (ScriptEngineFactory factory : factories) {
            System.out.println(factory.getNames());
        }
    }

    public void invokeExpression() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        String js = "1 + 2";
        Integer result = (Integer) engine.eval(js);
        System.out.println(result);
    }

    public void invokeFunction() throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        String js = "function welcom(){return 'welcom';}";
        engine.eval(js);
        Invocable invocable = (Invocable) engine;
        String result = (String) invocable.invokeFunction("welcom");
        System.out.println(result);
    }

    public void invokeFunctionWithParam() throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        String js = "function welcom(name){return 'welcom ' + name;}";
        engine.eval(js);
        Invocable invocable = (Invocable) engine;
        String result = (String) invocable.invokeFunction("welcom", "jianggujin");
        System.out.println(result);
    }

    public void inject() throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Date date = new Date();
        System.out.println(date.getTime());
        engine.put("date", date);
        String js = "function getTime(){return date.getTime();}";
        engine.eval(js);
        Invocable invocable = (Invocable) engine;
        Long result = (Long) invocable.invokeFunction("getTime");
        System.out.println(result);
    }

    public void runThread() throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        engine.put("out", System.out);
        String js = "var obj=new Object();obj.run=function(){out.println('thread...')}";
        engine.eval(js);
        Object obj = engine.get("obj");
        Invocable inv = (Invocable) engine;
        Runnable r = inv.getInterface(obj, Runnable.class);
        Thread t = new Thread(r);
        t.start();
    }

    public static void main(String[] args) throws NoSuchMethodException, ScriptException {
        new RunJavascript().runThread();
    }
}
