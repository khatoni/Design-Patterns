package bg.sofia.uni.fmi.designpatterns.task1;

import RandomNumberGenerator.Generatible;

public class CustomGeneratorRectangle implements Generatible {
    @Override
    public double generateRandomDouble() {
        return (int) 4.0;
    }
}
