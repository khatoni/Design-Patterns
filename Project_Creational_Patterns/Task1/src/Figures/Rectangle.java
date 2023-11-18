package Figures;

import java.util.Objects;

public class Rectangle implements Figure {

    private final double shortestSide;
    private final double longestSide;

    public Rectangle(double side1, double side2) {
        if (side1 <= 0 || side2 <= 0) {
            throw new IllegalArgumentException("One or more of the sides are with non positive values");
        }
        if (side1 > side2) {
            shortestSide = side2;
            longestSide = side1;
        } else {
            shortestSide = side1;
            longestSide = side2;
        }
    }

    public Rectangle(Rectangle other) {
        if (this == other || other == null) {
            throw new IllegalArgumentException("The argument is null or the same reference");
        }
        shortestSide = other.shortestSide;
        longestSide = other.longestSide;
    }

    public double getShortestSide() {
        return shortestSide;
    }

    public double getLongestSide() {
        return longestSide;
    }

    @Override
    public double calculatePerimeter() {
        return longestSide + shortestSide;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Rectangle that)) {
            return false;
        }

        return Double.compare(shortestSide, that.shortestSide) == 0 &&
                Double.compare(longestSide, that.longestSide) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortestSide, longestSide);
    }

    @Override
    public String toString() {
        return "rectangle " + shortestSide + " " + longestSide;
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this);
    }
}
