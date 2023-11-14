package bg.sofia.uni.fmi.designpatterns.task1;

import Figures.Circle;
import Figures.Figure;
import Factories.RandomFigureFactory;
import Figures.Rectangle;
import Figures.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomFigureFactoryTest {

    @Test
    void testCreationOfCircleWithValidRandomNumbers() {

        RandomFigureFactory figureFactory = new RandomFigureFactory(new CustomGeneratorCircle());
        Figure testFigure = figureFactory.createFigure();
        assertTrue(testFigure instanceof Circle);
    }

    @Test
    void testCreationOfRectangleWithValidNumbers() {
        RandomFigureFactory figureFactory = new RandomFigureFactory(new CustomGeneratorRectangle());
        Figure testFigure = figureFactory.createFigure();
        assertTrue(testFigure instanceof Rectangle);
    }

    @Test
    void testCreationOfTriangleWithValidNumbers() {
        RandomFigureFactory figureFactory = new RandomFigureFactory(new CustomGeneratorTriangle());
        Figure testFigure = figureFactory.createFigure();
        assertTrue(testFigure instanceof Triangle);
    }

}
