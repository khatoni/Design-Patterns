package RandomNumberGenerator;

import RandomNumberGenerator.Generatible;

import java.util.Random;

public class RandomGenerator implements Generatible {

    @Override
    public double generateRandomDouble() {
        Random random = new Random();
        String str = String.valueOf(random.nextDouble());
        String pressicion = str.substring(0, 4);
        return (Double.valueOf(pressicion) * 100);
    }
}
