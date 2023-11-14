package bg.sofia.uni.fmi.designpatterns.task1;

import RandomNumberGenerator.Generatible;

public class CustomGeneratorTriangle implements Generatible {
    @Override
    public double generateRandomDouble() {
        return (int) 5.0;
    }
}
