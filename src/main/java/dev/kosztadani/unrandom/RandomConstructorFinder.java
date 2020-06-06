package dev.kosztadani.unrandom;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * Finds the no-arg constructor of java.util.Random.
 */
public class RandomConstructorFinder {
    /**
     * @param classNode A ClassNode representing the class java.util.Random
     * @return The no-arg constructor of java.util.Random
     */
    public MethodNode getConstructor(ClassNode classNode) {
        for (MethodNode method : classNode.methods) {
            if (method.name.equals("<init>") && method.desc.equals("()V")) {
                return method;
            }
        }
        throw new IllegalStateException("Couldn't find no-arg constructor of java.util.Random");
    }
}
