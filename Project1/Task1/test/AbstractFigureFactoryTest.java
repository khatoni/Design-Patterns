import Factories.AbstractFigureFactory;
import Factories.FigureFactory;
import Factories.RandomFigureFactory;
import Factories.StreamFigureFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractFigureFactoryTest {

    @Test
    void testCreationOfRandomFigureFactory() {
        FigureFactory factory = new AbstractFigureFactory().create("random");
        assertTrue(factory instanceof RandomFigureFactory);
    }

    @Test
    void testCreationOfStreamFigureFactoryFromStdIn() {
        FigureFactory factory = new AbstractFigureFactory().create("stdin");
        assertTrue(factory instanceof StreamFigureFactory);
    }

    @Test
    void testCreationOfStreamFigureFactoryForInvalidFile() {
        FigureFactory factory = new AbstractFigureFactory().create("file filepath");
        assertNull(factory, "Factory should be null for invalid filepath");
    }

    @Test
    void testCreationOfStreamFigureFactoryForValidFile() {
        FigureFactory factory = new AbstractFigureFactory().create("file C:\\Users\\Hp\\Desktop\\Design-Patterns\\Task1\\resources\\alabala.txt");
        assertTrue(factory instanceof StreamFigureFactory,
                "The returned factory should be instance of StreamFigureFactory");
    }

    @Test
    void testCreationOfFactoryFromWrongCommand() {
        FigureFactory factory = new AbstractFigureFactory().create("test");
        assertNull(factory);
    }
}
