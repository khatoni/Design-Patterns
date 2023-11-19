package Factories;

import Figures.Circle;
import Figures.Figure;
import Figures.Rectangle;
import Figures.Triangle;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class FigureFromString {
    private static Triangle parseTriangle(Scanner scanner) {
        double firstSide;
        double secondSide;
        double thirdSide;
        try {
            firstSide = Double.parseDouble(scanner.next());
            secondSide = Double.parseDouble(scanner.next());
            thirdSide = Double.parseDouble(scanner.next());
        } catch (NumberFormatException | NoSuchElementException | IllegalStateException e) {
            return null;
        }
        if (scanner.hasNext()) {
            return null;
        }
        try {
            return new Triangle(firstSide, secondSide, thirdSide);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static Rectangle parseRectangle(Scanner scanner) {
        double firstSide;
        double secondSide;
        try {
            firstSide = Double.parseDouble(scanner.next());
            secondSide = Double.parseDouble(scanner.next());
        } catch (NumberFormatException | NoSuchElementException | IllegalStateException e) {
            return null;
        }
        if (scanner.hasNext()) {
            return null;
        }
        return new Rectangle(firstSide, secondSide);
    }

    private static Circle parseCircle(Scanner scanner) {
        double radius;
        try {
            radius = Double.parseDouble(scanner.next());
        } catch (NumberFormatException | NoSuchElementException | IllegalStateException e) {
            return null;
        }
        if (scanner.hasNext()) {
            return null;
        }
        return new Circle(radius);
    }

    public static Figure createFigureFromString(String str) {
        if (str == null || str.isBlank()) {
            throw new IllegalArgumentException("The string as an argument cannot be null");
        }
        Scanner scanner = new Scanner(str);
        scanner.useDelimiter(" ");
        String shape = scanner.next();
        Figure answer = switch (shape) {
            case "circle" -> {
                yield parseCircle(scanner);
            }
            case "rectangle" -> {
                yield parseRectangle(scanner);
            }
            case "triangle" -> {
                yield parseTriangle(scanner);
            }
            default -> null;
        };
        scanner.close();
        return answer;
    }
}
