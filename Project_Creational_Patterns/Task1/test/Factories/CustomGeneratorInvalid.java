package Factories;

import RandomNumberGenerator.Generatible;

public class CustomGeneratorInvalid implements Generatible {
    @Override
    public double generateRandomDouble() {
        return -1;
    }
}
