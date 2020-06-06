package dev.kosztadani.unrandom;

import dev.kosztadani.unrandom.annotations.UseAgentWithoutArguments;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@UseAgentWithoutArguments
public class TestWithAgentWithoutArguments {

    @Test
    public void testSimple() {
        Random random = new Random();
        long long1 = random.nextLong();
        long long2 = random.nextLong();
        long long3 = random.nextLong();
        assertEquals(RandomConstants.LONG_1_WITH_SEED_0, long1);
        assertEquals(RandomConstants.LONG_2_WITH_SEED_0, long2);
        assertEquals(RandomConstants.LONG_3_WITH_SEED_0, long3);
    }
}
