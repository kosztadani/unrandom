package dev.kosztadani.unrandom;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

/**
 * Some utility functions related to the ASM library.
 */
public class AsmUtils {

    public static final int CLASS_READER_NO_OPTIONS = 0;
    public static final int CLASS_WRITER_NO_OPTIONS = 0;

    /**
     * Create a ClassNode from an array of bytes representing a class.
     *
     * @param classfileBuffer The array of bytes representing a class.
     * @return A ClassNode object.
     */
    public static ClassNode createClassNode(byte[] classfileBuffer) {
        ClassNode node = new ClassNode();
        ClassReader reader = new ClassReader(classfileBuffer);
        reader.accept(node, CLASS_READER_NO_OPTIONS);
        return node;
    }

    /**
     * Converts a ClassNode to an array of bytes representing a class.
     *
     * @param node A ClassNode object
     * @return An array of bytes representing a class.
     */
    public static byte[] convertToBytes(ClassNode node) {
        ClassWriter writer = new ClassWriter(CLASS_WRITER_NO_OPTIONS);
        node.accept(writer);
        return writer.toByteArray();
    }
}
