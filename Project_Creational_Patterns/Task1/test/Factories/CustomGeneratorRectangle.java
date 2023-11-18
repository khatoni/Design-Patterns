package Factories;

import RandomNumberGenerator.Generatible;

public class CustomGeneratorRectangle implements Generatible {
    @Override
    public double generateRandomDouble() {
        return (int) 4.0;
    }
}
