package com.hhy.agent.methodaspect;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.MethodVisitor;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/12 23:03
 */
public class MyTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader,
                            String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer
    ) throws IllegalClassFormatException {
        if (!className.startsWith("java/lang")) {
            return classfileBuffer;
        }
        try {
            ClassReader reader = new ClassReader(classfileBuffer);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new byte[0];
    }
    private static class MyClassVisitor extends ClassVisitor {
        public MyClassVisitor(ClassWriter writer) {
            super(Opcodes.ASM7, writer);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

            MethodVisitor visitor = super.visitMethod(access, name, descriptor, signature, exceptions);
            if ("toString".equals(name)) {
                return new MyMethodVisitor(visitor);
            }
            return visitor;
        }
    }

    private static class MyMethodVisitor extends MethodVisitor {
        public MyMethodVisitor(MethodVisitor visitor) {
            super(Opcodes.ASM7, visitor);
        }

        @Override
        public void visitCode() {
            super.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream");
            super.visitLdcInsn("Modified toString method called");
            super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");

            super.visitCode();
        }
    }

}
