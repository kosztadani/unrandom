package dev.kosztadani.unrandom;

import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

/**
 * Transforms the no-arg constructor of java.util.Random.
 */
public class RandomConstructorTransformer {
    /**
     * @param classfileBuffer The bytes representing the class java.util.Random
     * @return A variant of java.util.Random that always gets seeded with 0.
     */
    public byte[] transformRandom(byte[] classfileBuffer) {
        ClassNode node = AsmUtils.createClassNode(classfileBuffer);
        RandomConstructorFinder finder = new RandomConstructorFinder();
        MethodNode constructor = finder.getConstructor(node);

        constructor.instructions.clear();

        constructor.instructions.add(new IntInsnNode(
                ALOAD,
                0
        ));
        constructor.instructions.add(new InsnNode(
                LCONST_0
        ));
        constructor.instructions.add(new MethodInsnNode(
                INVOKESPECIAL,
                "java/util/Random",
                "<init>",
                "(J)V"
        ));
        constructor.instructions.add(new InsnNode(
                RETURN
        ));

        constructor.localVariables.clear();

        return AsmUtils.convertToBytes(node);
    }
}
