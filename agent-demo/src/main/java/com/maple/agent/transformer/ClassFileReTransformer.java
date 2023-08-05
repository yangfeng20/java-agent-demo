package com.maple.agent.transformer;

import com.maple.agent.visitor.ObjectToStringPrintMethodVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangfeng
 * @date : 2023/8/4 22:13
 * desc:
 */

public class ClassFileReTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classFileBuffer) throws IllegalClassFormatException {
        if ("java/lang/Object".equals(className)) {
            ClassReader classReader = new ClassReader(classFileBuffer);
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            ClassVisitor classVisitor = new ObjectToStringPrintMethodVisitor(Opcodes.ASM9, classWriter);

            classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);
            return classWriter.toByteArray();
        }
        return null;
    }
}
