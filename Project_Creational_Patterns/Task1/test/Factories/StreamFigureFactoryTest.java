package Factories;

import Figures.Circle;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StreamFigureFactoryTest {

    @Test
    void createFigureFromFile() {
        Circle testCircle = new Circle(5.204);
        Scanner scanner = Mockito.mock();
        StreamFigureFactory streamFigureFactory = new StreamFigureFactory(scanner);
        Mockito.when(scanner.nextLine()).thenReturn(testCircle.toString());
        assertEquals(streamFigureFactory.createFigure(), testCircle);
        scanner.close();
    }

    @Test
    void createInvalidFigureFromFile() {
        Scanner scanner = Mockito.mock();
        StreamFigureFactory streamFigureFactory = new StreamFigureFactory(scanner);
        Mockito.when(scanner.nextLine()).thenReturn("");
        assertNull(streamFigureFactory.createFigure());
        scanner.close();
    }

    @Test
    void createFigureFromStdIn() throws IOException {
        String testInput = "rectangle 5.342 10.234";
        InputStream stream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(stream);
        StreamFigureFactory factory = new StreamFigureFactory(new Scanner(System.in));
        assertEquals(factory.createFigure().toString(), testInput);
        stream.close();
    }
}

