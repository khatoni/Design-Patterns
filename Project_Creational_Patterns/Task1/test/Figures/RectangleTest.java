package Figures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

    @Test
    void testRectangleConstructorWithNonPositiveSides() {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(-1, 5),
                "Rectangle should have only sides with positive value");
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(7, -3),
                "Rectangle should have only sides with positive value");
    }

    @Test
    void testRectangleConstructorSuccessfullyExecuted() {
        Rectangle rectangle = new Rectangle(23, 7);
        assertEquals(7, rectangle.getShortestSide());
        assertEquals(23, rectangle.getLongestSide());
    }

    @Test
    void testCopyConstructorWithValidObject() {
        Rectangle attempt = new Rectangle(6, 8);
        Rectangle copy = new Rectangle(attempt);
        assertEquals(attempt, copy, "The two objects should be equal");
    }

    @Test
    void testCopyConstructorWithNull() {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(null),
                "Should not provide null as parameter in copy constructor");
    }

    @Test
    void testPerimeterIsCalculatedCorrectlyWithPrecision() {
        Rectangle rectangle1 = new Rectangle(1.25, 7.65);
        assertEquals(8.90, rectangle1.calculatePerimeter(), 0.0001,
                "The expected value is not satisfied");
        Rectangle rectangle2 = new Rectangle(12.38, 15.20);
        assertEquals(27.58, rectangle2.calculatePerimeter(), 0.001,
                "The expected value is not satisfied");
    }

    @Test
    void testEqualsWithTwoDifferentTypes() {
        Rectangle rectangle = new Rectangle(5, 10);
        Object obj = new Object();
        assertNotEquals(rectangle, obj, "Expected false, because the objects are different");
    }

    @Test
    void testEqualsWithTheSameObject() {
        Rectangle rectangle = new Rectangle(12, 12);
        assertEquals(rectangle, rectangle, "The circles must be not the same object");
    }

    @Test
    void testEqualsWithTwoDifferentRectangles() {
        Rectangle rectangle1 = new Rectangle(8, 6);
        Rectangle rectangle2 = new Rectangle(6, 9);
        assertNotEquals(rectangle1, rectangle2, "The triangles are different, but the function returned true");
    }

    @Test
    void testEqualsWithTwoEqualRectangles() {
        Rectangle rectangle1 = new Rectangle(9, 6);
        Rectangle rectangle2 = new Rectangle(6, 9);
        assertEquals(rectangle1, rectangle2, "The triangles are equal, but the function returned false");
    }

    @Test
    void testHashCodeIsCorrectlyCalculated() {
        Rectangle rectangle1 = new Rectangle(9, 6);
        Rectangle rectangle2 = new Rectangle(6, 9);
        assertTrue(rectangle1.equals(rectangle2) && rectangle2.equals(rectangle1));
        assertEquals(rectangle1.hashCode(), rectangle2.hashCode(), "The hashCodes must be the same");
    }

    @Test
    void testCloneMethodIsCorrectlyExecuted() {
        Rectangle rectangle1 = new Rectangle(16.53, 6.267);
        Rectangle rectangle2 = rectangle1.clone();
        assertTrue(rectangle1.equals(rectangle2) && rectangle2.equals(rectangle1),
                "The objects must be the exact copy of each other");
    }

    @Test
    void testToStringMethodForTriangle() {
        Rectangle attempt = new Rectangle(16.53, 6.267);
        String str = attempt.toString();
        assertNotEquals(null, str,
                "The string should be not null");
        String[] array = str.split(" ");
        assertEquals(3, array.length,
                "The string must contain 3 elements");
        assertEquals("rectangle", array[0],
                "The first part of the string should be rectangle");
        assertEquals(Double.toString(attempt.getShortestSide()), array[1],
                "The first side length must be equal to the shortest side");
        assertEquals(Double.toString(attempt.getLongestSide()), array[2],
                "The second side length must be equal to the longest side");
    }
}
