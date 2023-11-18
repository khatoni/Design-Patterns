package Factories;

import RandomNumberGenerator.Generatible;

public class CustomGeneratorCircle implements Generatible {
    @Override
    public double generateRandomDouble() {
        return (int) 3.0;
    }
}
