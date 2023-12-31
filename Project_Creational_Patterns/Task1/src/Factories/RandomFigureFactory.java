package Factories;

import Figures.Circle;
import Figures.Figure;
import Figures.Rectangle;
import Figures.Triangle;
import RandomNumberGenerator.Generatible;

public class RandomFigureFactory implements FigureFactory {

    private final Generatible generatible;

    public RandomFigureFactory(Generatible generatible) {
        this.generatible = generatible;
    }

    @Override
    public Figure createFigure() {
        Figure figure;
        int randNumber = (int) generatible.generateRandomDouble() % 3;
        figure = switch (randNumber) {
            case 0 -> {
                double radius = generatible.generateRandomDouble();
                yield new Circle(radius);
            }
            case 1 -> {
                double firstSide = generatible.generateRandomDouble();
                double secondSide = generatible.generateRandomDouble();
                yield new Rectangle(firstSide, secondSide);
            }
            case 2 -> {
                Triangle triangle = null;
                do {
                    double firstSide = generatible.generateRandomDouble();
                    double secondSide = generatible.generateRandomDouble();
                    double thirdSide = generatible.generateRandomDouble();
                    try {
                        triangle = new Triangle(firstSide, secondSide, thirdSide);
                    } catch (IllegalArgumentException e) {
                        triangle = null;
                    }
                }
                while (triangle == null);
                yield triangle;

            }
            default -> throw new IllegalStateException("Unexpected value: " + randNumber);
        };
        return figure;
    }
}
