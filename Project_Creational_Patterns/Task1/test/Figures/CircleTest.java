package Figures;

import Figures.Circle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CircleTest {

    @Test
    void testCircleIsCreatedWithNegativeRadius() {
        assertThrows(IllegalArgumentException.class, () -> new Circle(-1),
                "Circle with negative radius cannot be created, IllegalArgumentException is expected");
    }

    @Test
    void testCircleIsCreatedWithRadiusZero() {
        assertThrows(IllegalArgumentException.class, () -> new Circle(0),
                "Circle with radius zero cannot be created,IllegalArgumentException is expected");
    }

    @Test
    void testCircleIsCreatedSuccessfully() {
        //should I make type checking
        Circle firstAttempt = new Circle(7.56);
        assertEquals(7.56, firstAttempt.getRadius(), 0.000001, "The expected radius should be 7.56");
        Circle secondAttempt = new Circle(0.00005);
        assertEquals(0.00005, secondAttempt.getRadius(), 0.00000001, "The expected radius is 0.00005");
    }

    @Test
    void testPerimeterIsCalculatedCorrectlyWithPrecisionForRadius10() {
        Circle tmp = new Circle(10);
        assertEquals(62.831, tmp.calculatePerimeter(), 0.001,
                "Expected value for the perimeter with radius 10 should be 62.8");
        assertEquals(62.8318, tmp.calculatePerimeter(), 0.0001,
                "Expected value for the perimeter with radius 10 should be 62.8");
        assertEquals(62.83185, tmp.calculatePerimeter(), 0.00001,
                "Expected value for the perimeter with radius 10 should be 62.8");
    }

    @Test
    void testPerimeterIsCalculatedCorrectlyWithPrecisionForRadius1() {
        Circle tmp = new Circle(1);
        assertEquals(6.283, tmp.calculatePerimeter(), 0.001,
                "Expected value for the perimeter with radius 10 should be 62.8");
        assertEquals(6.2831, tmp.calculatePerimeter(), 0.0001,
                "Expected value for the perimeter with radius 10 should be 62.8");
        assertEquals(6.28318, tmp.calculatePerimeter(), 0.00001,
                "Expected value for the perimeter with radius 10 should be 62.8");
    }

    //How to name the test for toString with different radiuses or just toString
    @Test
    void testToStringMethodForCircle() {
        Circle attempt = new Circle(3.121);
        String str = attempt.toString();
        assertNotEquals(null, str, "The string should be not null");
        String[] array = str.split(" ");
        assertEquals("circle", array[0], "The first part of the string should be circle");
        assertEquals(Double.toString(attempt.getRadius()), array[1], "The second part of the string should be equal to the radius");
    }

    @Test
    void testCopyConstructorWithValidObject() {
        Circle attempt = new Circle(7.54542);
        Circle copy = new Circle(attempt);
        assertEquals(attempt, copy, "The two objects should be equal");
    }

    @Test
    void testCopyConstructorWithNull() {
        assertThrows(IllegalArgumentException.class, () -> new Circle(null),
                "Should not provide null as parameter in copy constructor");
    }

    @Test
    void testEqualsWithTwoDifferentTypes() {
        // should I use always some random constants or declare default constructor
        Circle circle = new Circle(5);
        Object obj = new Object();
        assertNotEquals(circle, obj, "Expected false, because the objects are different");
    }

    @Test
    void testEqualsWithTheSameObject() {
        // should I use always some random constants or declare default constructor
        Circle circle = new Circle(5);
        assertEquals(circle, circle, "The circles must be not the same object");
    }

    @Test
    void testEqualsWithTwoDifferentCircles() {
        // should I use always some random constants or declare default constructor
        Circle circle1 = new Circle(7.5454);
        Circle circle2 = new Circle(8.0001);
        assertNotEquals(circle1, circle2, "The circles are different, but the function returned true");
    }

    @Test
    void testEqualsWithTwoEqualCircles() {
        // should I use copy constructor here
        Circle circle1 = new Circle(7.5454);
        Circle circle2 = new Circle(7.5454);
        assertEquals(circle1, circle2, "The circles are equal, but the function returned false");
    }

    @Test
    void testHashCodeIsCorrectlyCalculated() {
        Circle circle1 = new Circle(1.232);
        Circle circle2 = new Circle(1.232);
        assertTrue(circle1.equals(circle2) && circle2.equals(circle1));
        assertEquals(circle1.hashCode(), circle2.hashCode(), "The hashCodes must be the same");
    }

    @Test
    void testCloneMethodIsCorrectlyExecuted() {
        Circle circle1 = new Circle(101.231);
        Circle circle2 = circle1.clone();
        assertTrue(circle1.equals(circle2) && circle2.equals(circle1),
                "The objects must be the exact copy of each other");
    }

}
