package Factories;

import Figures.Circle;
import Figures.Figure;
import Figures.Rectangle;
import Figures.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomFigureFactoryTest {

    @Test
    void testCreationOfCircleWithValidRandomNumbers() {

        RandomFigureFactory figureFactory = new RandomFigureFactory(new CustomGeneratorCircleStub());
        Figure testFigure = figureFactory.createFigure();
        assertTrue(testFigure instanceof Circle);
    }

    @Test
    void testCreationOfRectangleWithValidNumbers() {
        RandomFigureFactory figureFactory = new RandomFigureFactory(new CustomGeneratorRectangleStub());
        Figure testFigure = figureFactory.createFigure();
        assertTrue(testFigure instanceof Rectangle);
    }

    @Test
    void testCreationOfTriangleWithValidNumbers() {
        RandomFigureFactory figureFactory = new RandomFigureFactory(new CustomGeneratorTriangleStub());
        Figure testFigure = figureFactory.createFigure();
        assertTrue(testFigure instanceof Triangle);
    }

    @Test
    void testCreationOfFigureWithInvalidRandomNumber() {
        RandomFigureFactory figureFactory = new RandomFigureFactory(new CustomGeneratorInvalidStub());
        assertThrows(IllegalStateException.class, figureFactory::createFigure);
    }

}
