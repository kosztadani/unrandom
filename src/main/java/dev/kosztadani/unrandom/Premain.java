package dev.kosztadani.unrandom;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * This is the Premain-Class of the jar built from this project.
 */
public class Premain {
    /**
     * This is the entrypoint, as defined by the Instrumentation API.
     *
     * @param args Any arguments passed to the agent.
     * @param inst The Instrumentation object, supplied by the JVM.
     */
    public static void premain(String args, Instrumentation inst) {
        new Premain().init(inst);
    }

    private void init(Instrumentation inst) {
        ClassFileTransformer transformer = new UnrandomTransformer();
        inst.addTransformer(transformer);
    }
}
