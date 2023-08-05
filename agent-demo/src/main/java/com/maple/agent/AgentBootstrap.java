package com.maple.agent;

import com.maple.agent.transformer.ClassFileReTransformer;

import java.lang.instrument.Instrumentation;

/**
 * @author yangfeng
 * @date : 2023/8/4 17:54
 * desc:
 */

public class AgentBootstrap {

    /**
     * premain所
     * 静态加载方式；-javaagent参数在在启动时manifest ：指定PerMainClass
     *
     * @param args            args
     * @param instrumentation 仪表
     */
    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("------------------------perMain------------------------");
        transformer(instrumentation);
    }


    private static void transformer(Instrumentation instrumentation){
        ClassFileReTransformer transformer = new ClassFileReTransformer();
        instrumentation.addTransformer(transformer);

        Class<?>[] classes = instrumentation.getAllLoadedClasses();
        for (Class<?> clazz : classes) {
            try {
                System.out.println("retransform：" + clazz);
                instrumentation.retransformClasses(clazz);
            } catch (Throwable e) {
                System.out.println(clazz.getName() + "重新转换出现异常：" + e.getClass().getSimpleName() + (e.getMessage() == null ? "" : "  msg: "+ e.getMessage()));
            }
        }
    }

    /**
     * agentmain
     * 动态加载方式；在启动之后附加进去，manifest：指定AgentClass
     *
     * @param args            args
     * @param instrumentation 仪表
     */
    public static void agentmain(String args, Instrumentation instrumentation) {
        System.out.println("-------------------agentMain------------------");
    }
}
