package FigureCollection;

import Factories.AbstractFigureFactory;
import Factories.FigureFactory;
import Figures.Figure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


public class FiguresCollectionTest {

    private FiguresCollection collection;

    @BeforeEach
    void initialise() {
        collection = new FiguresCollection();
        String triangleFromIn = "triangle 3.5 4.5 5.5\n" +
                "rectangle 10 15\n" +
                "circle 5.84\n" +
                "circle 7.45";
        InputStream inputStream = new ByteArrayInputStream(triangleFromIn.getBytes());
        System.setIn(inputStream);
        FigureFactory factory = new AbstractFigureFactory().create("stdin");
        for (int i = 0; i < 4; i++) {
            collection.addFigure(factory.createFigure());
        }
       /*
        collection.addFigure(new AbstractFigureFactory().create("random").createFigure());
        collection.addFigure(new AbstractFigureFactory().create("random").createFigure());
        collection.addFigure(new AbstractFigureFactory().create("random").createFigure());
        collection.addFigure(new AbstractFigureFactory().create("random").createFigure());
        collection.addFigure(new AbstractFigureFactory().create("random").createFigure());

        */
    }

    @Test
    void testAddFigure() {

    }

    @Test
    void testGetElementAtInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> collection.getAt(-2));
        assertThrows(IndexOutOfBoundsException.class, () -> collection.getAt(15));
    }

    @Test
    void testCloneAtIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> collection.clone(-1),
                "IllegalArgument exception expected for index out of bounds");
        assertThrows(IndexOutOfBoundsException.class, () -> collection.clone(10),
                "IllegalArgument exception expected for index out of bounds");
    }

    @Test
    void testCloneAtCorrectIndex() {
        Figure tmpFigureAtIndexZero = collection.clone(0);
        Figure tmpFigureAtIndexOne = collection.clone(1);
        Figure tmpFigureAtIndexTwo = collection.clone(2);
        Figure tmpFigureAtIndexThree = collection.clone(3);
        assertTrue(tmpFigureAtIndexZero.equals(collection.getAt(0)));
        assertTrue(tmpFigureAtIndexOne.equals(collection.getAt(1)));
        assertTrue(tmpFigureAtIndexTwo.equals(collection.getAt(2)));
        assertTrue(tmpFigureAtIndexThree.equals(collection.getAt(3)));
    }

    @Test
    void testDeleteFigureAtInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> collection.deleteAtIndex(-2),
                "IllegalArgumentException is expected because of negative index or out of bounds");
        assertThrows(IndexOutOfBoundsException.class, () -> collection.deleteAtIndex(15),
                "IllegalArgumentException is expected because of negative index or out of bounds");
    }

    @Test
    void testDeleteFigureAtValidIndex() {
        int collectionSizeBeforeDeletion = collection.size();
        Figure beforeDeletion = collection.getAt(2);
        Figure afterDeletion = collection.deleteAtIndex(2);
        assertTrue(beforeDeletion.equals(afterDeletion));
        int collectionSizeAfterDeletion = collection.size();
        assertTrue(collectionSizeBeforeDeletion - 1 == collectionSizeAfterDeletion);
    }

    @Test
    void testPrintToStdOut() {
        String printAnswer = "triangle 3.5 4.5 5.5\nrectangle 10.0 15.0\ncircle 5.84\ncircle 7.45\n";
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
        PrintStream out = System.out;
        PrintStream capture = new PrintStream(testOutput);
        System.setOut((capture));
        collection.print(System.out);
        System.setOut(out);
        String capturedString = testOutput.toString(StandardCharsets.UTF_8);
        System.out.println(capturedString);
        // assertEquals(capturedString.trim(),printAnswer.trim());
        assertEquals(capturedString.replaceAll("\\s", ""), printAnswer.replaceAll("\\s", ""));
    }

    @Test
    void testPrintToFile() {
        String printAnswer = "triangle 3.5 4.5 5.5\nrectangle 10.0 15.0\ncircle 5.84\ncircle 7.45\n";
        String filepath = "C:\\Users\\Hp\\Desktop\\Design-Patterns\\Project1\\Task1\\resources\\alabala.txt";
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
        PrintStream fileStream = null;
        try {
            fileStream = new PrintStream(filepath);
            collection.print(fileStream);
        } catch (FileNotFoundException e) {

        }
        String content="";
        try {
            List<String> lines = Files.readAllLines(Paths.get(filepath));
            for(int i =0 ;i<lines.size();i++) {
                content+= lines.get(i);
            }
        }
        catch(IOException e) {}

        System.out.println(content);
        System.out.println(testOutput.toString(StandardCharsets.UTF_8));

        assertEquals(content.replaceAll("\\s", ""), printAnswer.replaceAll("\\s", ""));
    }

}
