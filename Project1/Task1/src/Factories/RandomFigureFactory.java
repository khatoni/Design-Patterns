package Factories;

import RandomNumberGenerator.Generatible;
import Figures.Circle;
import Figures.Figure;
import Figures.Rectangle;
import Figures.Triangle;

public class RandomFigureFactory implements FigureFactory {

    private final Generatible generatible;

    public RandomFigureFactory(Generatible generatible) {
        this.generatible = generatible;
    }

    @Override
    public Figure createFigure() {

        int randNumber = (int) (generatible.generateRandomDouble()) % 3;
        switch (randNumber) {
            case 0 -> {
                double radius = generatible.generateRandomDouble();
                return new Circle(radius);
            }
            case 1 -> {
                double firstSide = generatible.generateRandomDouble();
                double secondSide = generatible.generateRandomDouble();
                return new Rectangle(firstSide, secondSide);
            }
            case 2 -> {
                double firstSide = generatible.generateRandomDouble();
                double secondSide = generatible.generateRandomDouble();
                double thirdSide = generatible.generateRandomDouble();
                try {
                    // trybva vinagi da vrushta valid figura
                    return new Triangle(firstSide, secondSide, thirdSide);
                } catch (IllegalArgumentException e) {
                    return null;
                }
            }
            default -> {
                return null;
            }
        }
    }
}
