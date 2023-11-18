package Factories;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

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
    void testCreationOfStreamFigureFactoryForValidFile() throws IOException {
        String fileName = "C:\\Users\\Hp\\Desktop\\Design-Patterns\\Project1\\Task1\\testAbstractFactory.txt";
        File tmpFile = new File(fileName);
        tmpFile.createNewFile();
        FigureFactory factory = new AbstractFigureFactory().create("file " + fileName);
        assertTrue(factory instanceof StreamFigureFactory,
                "The returned factory should be instance of StreamFigureFactory");
        tmpFile.delete();
    }

    @Test
    void testCreationOfFactoryFromWrongCommand() {
        FigureFactory factory = new AbstractFigureFactory().create("test");
        assertNull(factory);
    }
}
