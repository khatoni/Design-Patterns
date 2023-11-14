package bg.sofia.uni.fmi.designpatterns.task1;

import RandomNumberGenerator.Generatible;

public class CustomGeneratorCircle implements Generatible {
    @Override
    public double generateRandomDouble() {
        return (int) 3.0;
    }
}
