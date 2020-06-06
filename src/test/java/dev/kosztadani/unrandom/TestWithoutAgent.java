package dev.kosztadani.unrandom;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWithoutAgent {
    /**
     * Test uninstrumented java.util.Random seeded with 0.
     */
    @Test
    public void sanityTest() {
        Random random = new Random(0);
        long long1 = random.nextLong();
        long long2 = random.nextLong();
        long long3 = random.nextLong();
        assertEquals(RandomConstants.LONG_1_WITH_SEED_0, long1);
        assertEquals(RandomConstants.LONG_2_WITH_SEED_0, long2);
        assertEquals(RandomConstants.LONG_3_WITH_SEED_0, long3);
    }
}
