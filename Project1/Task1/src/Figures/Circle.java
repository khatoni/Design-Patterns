package Figures;

import java.util.Objects;

public class Circle implements Figure {

    private final double radius;

    public Circle(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Circle with negative radius cannot be instantiated");
        }
        this.radius = radius;
    }

    public Circle(Circle other) {
        if (this == other || other == null) {
            throw new IllegalArgumentException("The argument should not be null or the same reference");
        }
        this.radius = other.radius;

    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * radius * Math.PI;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Circle that)) {
            return false;
        }

        return Double.compare(radius, that.radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }

    @Override
    public String toString() {
        return "circle " + radius;
    }

    @Override
    public Circle clone() {
        return new Circle(this);
    }
}
