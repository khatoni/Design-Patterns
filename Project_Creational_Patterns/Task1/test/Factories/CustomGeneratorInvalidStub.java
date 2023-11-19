package Factories;

import RandomNumberGenerator.Generatible;

public class CustomGeneratorInvalidStub implements Generatible {
    @Override
    public double generateRandomDouble() {
        return -1;
    }
}
