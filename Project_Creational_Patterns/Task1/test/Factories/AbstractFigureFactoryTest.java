package Factories;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractFigureFactoryTest {

    @Test
    void testCreationOfRandomFigureFactory() {
        FigureFactory factory = AbstractFigureFactory.create("random");
        assertTrue(factory instanceof RandomFigureFactory);
    }

    @Test
    void testCreationOfStreamFigureFactoryFromStdIn() {
        FigureFactory factory = AbstractFigureFactory.create("stdin");
        assertTrue(factory instanceof StreamFigureFactory);
    }

    @Test
    void testCreationOfStreamFigureFactoryForInvalidFile() {
        FigureFactory factory = AbstractFigureFactory.create("file filepath");
        assertNull(factory, "Factory should be null for invalid filepath");
    }

    @Test
    void testCreationOfStreamFigureFactoryForValidFile() throws IOException {
        String fileName = "resources/testAbstractFactory.txt";
        Path tmpFile = Path.of(fileName);
        Files.createFile(tmpFile);
        FigureFactory factory = AbstractFigureFactory.create("file " + fileName);
        assertTrue(factory instanceof StreamFigureFactory,
                "The returned factory should be instance of StreamFigureFactory");
        ((StreamFigureFactory) factory).close();
        Files.deleteIfExists(tmpFile);

    }

    @Test
    void testCreationOfFactoryFromWrongCommand() {
        FigureFactory factory = AbstractFigureFactory.create("test");
        assertNull(factory);
    }
}
