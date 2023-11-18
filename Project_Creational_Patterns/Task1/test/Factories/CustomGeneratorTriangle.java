package Factories;

import RandomNumberGenerator.Generatible;

public class CustomGeneratorTriangle implements Generatible {
    @Override
    public double generateRandomDouble() {
        return (int) 5.0;
    }
}
