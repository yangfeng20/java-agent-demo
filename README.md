### Java agent

Java Agent 是 Java 虚拟机 (JVM) 提供的一个机制，可以在运行时修改和增强 Java 应用程序的行为。

它是Jdk1.5之后引入的,简单来说就是可以去重新定义类，转换类; 在内存层面修改字节码。

有人可能知道asm技术，asm的话，它也可以用来修改字节码，但是它修改的字节码，是重新生成一个新的Class，而不能替换内存中已经存在的Class，需要我们自定义classLoader去使用。

准确来说，java agent没有提供修改字节码的能力，而是提供转换类的时机和入口，转换类的能力可以通过各种字节码操作框架去实现。
所以，当Java Agent和Asm技术结合，就能碰撞出火花，可以去修改java层面的源代码。

arthas 

skywalking

### MANIFEST.MF

- Premain-Class: com.maple.agent.AgentBootstrap
- permain方法的入口类，静态启动方式，该方法在main方法之前执行 java -javaagent:xx.jar App
-
- Agent-Class: com.maple.agent.AgentBootstrap
- agentmain方法的入口类,动态加载方式，在应用程序启动之后动态的attach进目标虚拟机中
-
- Can-Redefine-Classes: true
- 支持重新定义类【Instrumentation.redefineClasses】
-
- Can-Retransform-Classes: true
- 支持重新转换类 【Instrumentation.retransformClasses】
-
- Can-Set-Native-Method-Prefix: true
- 支持本地方法前缀【Instrumentation.setNativeMethodPrefix】
- 用于hook native本地方法
-
- Manifest-Version: 1.0

### Instrumentation

- addTransformer 添加一个ClassFile转换器，转换未加载的类，当发生类加载时，ClassFileTransformer.transform方法被调用。
- 相当于一个类加载时的拦截器，而我们可以在这个拦截器中修改类文件结构，以达到修改源代码的目的。
- 
- retransformClasses 重新转换类，需要要当前注册的类文件转换器支持重新转换，否则不会调用transform方法。
- 