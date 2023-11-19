package RandomNumberGenerator;

import java.util.Random;

public class RandomGenerator implements Generatible {

    private final Random random;

    public RandomGenerator(Random random) {
        this.random = random;
    }

    @Override
    public double generateRandomDouble() {
        String str = String.valueOf(random.nextDouble());
        String precision = str.substring(0, 4);
        return (Double.parseDouble(precision) * 100);
    }
}
