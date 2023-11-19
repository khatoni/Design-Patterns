package Factories;

import Figures.Circle;
import Figures.Figure;
import Figures.Rectangle;
import Figures.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FigureFactoryTest {

    @Test
    void testPassingNullAsArgument() {
        assertThrows(IllegalArgumentException.class, () -> FigureFromString.createFigureFromString(null));
    }

    @Test
    void testPassingBlankStringAsArgument() {
        assertThrows(IllegalArgumentException.class, () -> FigureFromString.createFigureFromString(" "));
    }

    @Test
    void testPassingStringWithWrongFigure() {
        assertNull(FigureFromString.createFigureFromString("circl 20.4"));
        assertNull(FigureFromString.createFigureFromString("recangle 1292.1"));
        assertNull(FigureFromString.createFigureFromString("tringle 1 1 1"));
        assertNull(FigureFromString.createFigureFromString("polygon 1 1 1 1"));
    }

    @Test
    void testCreatingRectangleWithoutSideValues() {
        String string = "rectangle    ";
        assertNull(FigureFromString.createFigureFromString(string));
    }

    @Test
    void testCreatingRectangleWithMoreThanTwoSideValues() {
        assertNull(FigureFromString.createFigureFromString("rectangle 10.234 54.23 8"));
    }

    @Test
    void testCreatingRectangleWithOneSide() {

        assertNull(FigureFromString.createFigureFromString("rectangle 1023.12    "));
    }

    @Test
    void testCreatingRectangleWithTwoSidesInvalidValues() {

        assertNull(FigureFromString.createFigureFromString("rectangle 102a 23.4"));
        assertNull(FigureFromString.createFigureFromString("rectangle 1.23202 34:234"));
        assertNull(FigureFromString.createFigureFromString("rectangle 1.2r3202 34:2a34"));
    }

    @Test
    void testCorrectlyCreatingRectangle() {
        String str = "rectangle 5.26 8.2434";
        Figure figure = FigureFromString.createFigureFromString(str);
        assertTrue(figure instanceof Rectangle, "The figure returned is not Rectangle");
        assertEquals(figure.toString(), str, "The string representation is not the same as the desired one");
    }

    @Test
    void testCreatingCircleWithoutRadiusValue() {
        String string = "circle    ";
        assertNull(FigureFromString.createFigureFromString(string));
    }

    @Test
    void testCreatingCircleWithMoreThanOneValue() {
        assertNull(FigureFromString.createFigureFromString("circle 10.234 54.23"));
    }

    @Test
    void testCreatingCircleWithInvalidRadius() {
        assertNull(FigureFromString.createFigureFromString("circle 1023p"));
        assertNull(FigureFromString.createFigureFromString("circle qwerty"));
        assertNull(FigureFromString.createFigureFromString("circle 10d2.0 "));
    }

    @Test
    void testCorrectlyCreatingCircle() {
        String str = "circle 5.26";
        Figure figure = FigureFromString.createFigureFromString(str);
        assertTrue(figure instanceof Circle, "The figure returned is not Circle");
        assertEquals(figure.toString(), str, "The string representation is not the same as the desired one");
    }

    @Test
    void testCreatingTriangleWithoutSideValues() {
        String string = "triangle    ";
        assertNull(FigureFromString.createFigureFromString(string));
    }

    @Test
    void testCreatingTriangleWithMoreThanThreeSideValues() {
        assertNull(FigureFromString.createFigureFromString("triangle 10.234 54.23 8 9"));
    }

    @Test
    void testCreatingTriangleWithLessThanThreeSides() {

        assertNull(FigureFromString.createFigureFromString("triangle 1023.12 "));
        assertNull(FigureFromString.createFigureFromString("triangle 12.21 434.13"));
    }

    @Test
    void testCreatingTriangleWithInvalidSideValues() {

        assertNull(FigureFromString.createFigureFromString("triangle 102a 23.4 8"));
        assertNull(FigureFromString.createFigureFromString("triangle 1.23202 34:234 10"));
        assertNull(FigureFromString.createFigureFromString("triangle 1.232202 34234 25.qwer"));
        assertNull(FigureFromString.createFigureFromString("triangle ad 242.34t 1*dw"));
    }

    @Test
    void testCreatingTriangleWithInvalidInequality() {
        assertNull(FigureFromString.createFigureFromString("triangle 15.23 11.122 232.23"));
        assertNull(FigureFromString.createFigureFromString("triangle 20.89432 9.121323 10.0505505"));
    }

    @Test
    void testCorrectlyCreatingTriangle() {
        String str = "triangle 3.5 4.5 5.5";
        Figure figure = FigureFromString.createFigureFromString(str);
        assertTrue(figure instanceof Triangle, "The figure returned is not Rectangle");
        assertEquals(figure.toString(), str, "The string representation is not the same as the desired one");
    }

}
