package bg.sofia.uni.fmi.designpatterns.task1.triangle;

import Figures.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

    @Test
    void testTriangleConstructorWithNonPositiveSides() {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(-1, 5, 2),
                "Triangle should have only sides with positive value");
        assertThrows(IllegalArgumentException.class, () -> new Triangle(7, 0, 4),
                "Triangle should have only sides with positive value");
        assertThrows(IllegalArgumentException.class, () -> new Triangle(8, 3, -0.5),
                "Triangle should have only sides with positive value");
    }

    @Test
    void testTriangleConstructorWithInvalidTriangleInequality() {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(7, 1, 4),
                "Triangle should have sides, which satisfy the triangle inequality");
    }

    @Test
    void testTriangleConstructorSuccessfullyExecuted() {
        Triangle triangle = new Triangle(5, 3, 4);
        assertEquals(3, triangle.getShortestSide());
        assertEquals(4, triangle.getMiddleSide());
        assertEquals(5, triangle.getLongestSide());
    }

    @Test
    void testCopyConstructorWithValidObject() {
        Triangle attempt = new Triangle(6, 8, 10);
        Triangle copy = new Triangle(attempt);
        assertEquals(attempt, copy, "The two objects should be equal");
    }

    @Test
    void testCopyConstructorWithNull() {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(null),
                "Should not provide null as parameter in copy constructor");
    }

    @Test
    void testPerimeterIsCalculatedCorrectlyWithPrecision() {
        Triangle triangle1 = new Triangle(6, 8, 10);
        assertEquals(24, triangle1.calculatePerimeter(), 0.0001,
                "The expected value is not satisfied");
        Triangle triangle2 = new Triangle(4.18, 6.23, 5.05);
        assertEquals(15.46, triangle2.calculatePerimeter(), 0001,
                "The expected value is not satisfied");
    }

    @Test
    void testEqualsWithTwoDifferentTypes() {
        // should I use always some random constants or declare default constructor
        Triangle triangle = new Triangle(5, 6, 7);
        Object obj = new Object();
        assertNotEquals(triangle, obj, "Expected false, because the objects are different");
    }

    @Test
    void testEqualsWithTheSameObject() {
        // should I use always some random constants or declare default constructor
        Triangle triangle = new Triangle(5, 6, 7);
        assertEquals(triangle, triangle, "The circles must be not the same object");
    }

    @Test
    void testEqualsWithTwoDifferentTriangles() {
        // should I use always some random constants or declare default constructor
        Triangle triangle1 = new Triangle(6, 8, 10);
        Triangle triangle2 = new Triangle(6, 9, 10);
        assertNotEquals(triangle1, triangle2, "The triangles are different, but the function returned true");
    }

    @Test
    void testEqualsWithTwoEqualTriangles() {
        // should I use copy constructor here
        Triangle triangle1 = new Triangle(6, 8, 10);
        Triangle triangle2 = new Triangle(6, 8, 10);
        assertEquals(triangle1, triangle2, "The triangles are equal, but the function returned false");
    }

    @Test
    void testHashCodeIsCorrectlyCalculated() {
        Triangle triangle1 = new Triangle(6, 8, 10);
        Triangle triangle2 = new Triangle(6, 8, 10);
        assertTrue(triangle1.equals(triangle2) && triangle2.equals(triangle1));
        assertEquals(triangle1.hashCode(), triangle2.hashCode(), "The hashCodes must be the same");
    }

    @Test
    void testCloneMethodIsCorrectlyExecuted() {
        Triangle triangle1 = new Triangle(3, 4, 5);
        Triangle triangle2 = triangle1.clone();
        assertTrue(triangle1.equals(triangle2) && triangle2.equals(triangle1),
                "The objects must be the exact copy of each other");
    }

    @Test
    void testToStringMethodForTriangle() {
        Triangle attempt = new Triangle(4, 5, 3);
        String str = attempt.toString();
        assertNotEquals(null, str, "The string should be not null");
        String[] array = str.split(" ");
        assertEquals(4, array.length, "The string must contain 4 elements");
        assertEquals("triangle", array[0], "The first part of the string should be triangle");
        assertEquals(Double.toString(attempt.getShortestSide()), array[1], "The first side length must be equal to the shortest side");
        assertEquals(Double.toString(attempt.getMiddleSide()), array[2], "The second side length must be equal to the middle side");
        assertEquals(Double.toString(attempt.getLongestSide()), array[3], "The third side length must be equal to the longest side");
    }

}
