package Factories;

import Figures.Circle;
import Figures.Rectangle;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class StreamFigureFactoryTest {

    @Test
    void createFigureFromFile() {

        Circle testCircle = new Circle(5.204);
        String fileName = "C:\\Users\\Hp\\Desktop\\Design-Patterns\\Project1\\Task1\\testCreationOfCircle.txt";
        File tmpFile = new File(fileName);
        try {
            if (tmpFile.createNewFile()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                writer.write(testCircle.toString());
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Input output exception thrown in trying to create or write to a test file");
        }
        try {
            Scanner sc = new Scanner(new FileReader(fileName));
            StreamFigureFactory factory = new StreamFigureFactory(sc);
            assertEquals(testCircle, factory.createFigure());
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file to read from is not created in the test");
        }
        if (tmpFile.delete()) {
            System.out.println("deleted successfully");
        } else {
            System.out.println("Not deleted");
        }
    }

    @Test
    void createInvalidFigureFromFile() {

        Rectangle rectangle = new Rectangle(4.5, 5.5);
        String fileName = "C:\\Users\\Hp\\Desktop\\Design-Patterns\\Project1\\Task1\\testCreationOfCircle.txt";
        File tmpFile = new File(fileName);
        try {
            if (tmpFile.createNewFile()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                writer.write(rectangle.toString().substring(0,4));
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Input output exception thrown in trying to create or write to a test file");
        }
        try {
            Scanner sc = new Scanner(new FileReader(fileName));
            StreamFigureFactory factory = new StreamFigureFactory(sc);
            assertNull(factory.createFigure(), "The expected figure was null");
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file to read from is not created in the test");
        }
        if (tmpFile.delete()) {
            System.out.println("deleted successfully");
        } else {
            System.out.println("Not deleted");
        }

    }

    @Test
    void createFigureFromStdIn() {
        String testInput = "rectangle 5.342 10.234";
        InputStream stream = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(stream);
        StreamFigureFactory factory = new StreamFigureFactory(new Scanner(System.in));
        assertTrue(factory.createFigure().toString().equals(testInput));
    }
}

