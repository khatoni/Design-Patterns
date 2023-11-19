package Figures;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class Triangle implements Figure {
    private final double shortestSide;
    private final double middleSide;
    private final double longestSide;

    public Triangle(double side1, double side2, double side3) {

        if (side1 <= 0 || side2 <= 0 || side3 <= 0) {
            throw new IllegalArgumentException(
                    "One or more of the sides have not valid length(should be more than 0)");
        }

        if (!((side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1))) {
            throw new IllegalArgumentException(
                    "The lengths provided do not satisfy the triangle inequality theorem");
        }
        if (Double.compare(side1, side2) == 0 && Double.compare(side2, side3) == 0) {
            shortestSide = middleSide = longestSide = side1;
        } else {
            SortedSet<Double> tmp = new TreeSet<>() {
            };
            tmp.add(side1);
            tmp.add(side2);
            tmp.add(side3);

            shortestSide = tmp.first();
            tmp.remove(shortestSide);
            middleSide = tmp.first();
            longestSide = tmp.last();
        }
    }

    public Triangle(Triangle other) {
        if (this == other || other == null) {
            throw new IllegalArgumentException(
                    "The argument should not be null or the same reference");
        }
        this.shortestSide = other.shortestSide;
        this.middleSide = other.middleSide;
        this.longestSide = other.longestSide;
    }

    public double getShortestSide() {
        return shortestSide;
    }

    public double getMiddleSide() {
        return middleSide;
    }

    public double getLongestSide() {
        return longestSide;
    }

    @Override
    public double calculatePerimeter() {
        return shortestSide + middleSide + longestSide;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Triangle that)) {
            return false;
        }

        return Double.compare(shortestSide, that.shortestSide) == 0 &&
                Double.compare(middleSide, that.middleSide) == 0 &&
                Double.compare(longestSide, that.longestSide) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortestSide, middleSide, longestSide);
    }

    @Override
    public String toString() {
        return "triangle " + shortestSide + " " + middleSide + " " + longestSide;
    }

    @Override
    public Triangle clone() {
        return new Triangle(this);
    }
}
