package RandomNumberGenerator;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomNumberGeneratorTest {

    @Test
    void testGenerateRandomDouble() {
        Random random = Mockito.mock();
        Mockito.when(random.nextDouble()).thenReturn(0.1254);
        RandomGenerator randomGenerator = new RandomGenerator(random);

        assertEquals(12.0, randomGenerator.generateRandomDouble(), 0.0001);
    }
}
