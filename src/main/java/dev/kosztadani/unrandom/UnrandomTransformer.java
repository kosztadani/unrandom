package dev.kosztadani.unrandom;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * Implementation of ClassFileTransformer that will transform java.util.Random.
 */
public class UnrandomTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(
            ClassLoader classLoader,
            String className,
            Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain,
            byte[] classfileBuffer
    ) {

        if (shouldTransform(className)) {
            return doTransform(classfileBuffer);
        } else {
            return classfileBuffer;
        }
    }

    private boolean shouldTransform(String className) {
       return className.equals("java/util/Random");
    }

    private byte[] doTransform(byte[] classfileBuffer) {
       RandomConstructorTransformer transformer = new RandomConstructorTransformer();
       return transformer.transformRandom(classfileBuffer);
    }
}
