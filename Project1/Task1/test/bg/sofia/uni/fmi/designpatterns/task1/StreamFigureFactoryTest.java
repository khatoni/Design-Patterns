package bg.sofia.uni.fmi.designpatterns.task1;

import Factories.StreamFigureFactory;
import Figures.Circle;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamFigureFactoryTest {

    @Test
    void createFigureFromFile() {
        // kato testvam i znam che fila go ima kakvo pravim s try catch
        // suzdavame faila tuka da ne zavisim otvunka
        try {
            Circle testCircle = new Circle(5.204);
            Scanner sc = new Scanner(new FileReader("alabala.txt"));
            StreamFigureFactory factory = new StreamFigureFactory(sc);
            assertEquals(testCircle, factory.createFigure());
        } catch (FileNotFoundException e) {

        }
    }

    @Test
    void createFigureFromStdIn() {
        // kak da testvame ot inputa
        //system.in.setIn(");
        StreamFigureFactory factory = new StreamFigureFactory(new Scanner(System.in));


    }
}

