package FigureCollection;

import Factories.AbstractFigureFactory;
import Factories.FigureFactory;
import Figures.Circle;
import Figures.Figure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class FiguresCollectionTest {

    private FiguresCollection collection;

    @BeforeEach
    void initialise() {
        collection = new FiguresCollection();
        String triangleFromIn = """
                triangle 3.5 4.5 5.5
                rectangle 10 15
                circle 5.84
                circle 7.45""";
        InputStream inputStream = new ByteArrayInputStream(triangleFromIn.getBytes());
        System.setIn(inputStream);
        FigureFactory factory = AbstractFigureFactory.create("stdin");
        for (int i = 0; i < 4; i++) {
            collection.addFigure(factory.createFigure());
        }
    }

    @Test
    void testAddFigure() {
        Circle circle = new Circle(10.25);
        int sizeBeforeAdd = collection.size();
        collection.addFigure(circle);
        int sizeAfter = collection.size();
        assertEquals(sizeAfter - 1, sizeBeforeAdd,
                "The size after addition is not as expected");
        assertTrue(collection.contains(circle),
                "The collection must contain the figure after addition");
    }

    @Test
    void testGetElementAtInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> collection.getAt(-2),
                "The expected index should be non negative");
        assertThrows(IndexOutOfBoundsException.class, () -> collection.getAt(15),
                "The index should be less than the size");
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
        assertEquals(tmpFigureAtIndexZero, collection.getAt(0),
                "The figures must be the same after cloning");
        assertEquals(tmpFigureAtIndexOne, collection.getAt(1),
                "The figures must be the same after cloning");
        assertEquals(tmpFigureAtIndexTwo, collection.getAt(2),
                "The figures must be the same after cloning");
        assertEquals(tmpFigureAtIndexThree, collection.getAt(3),
                "The figures must be the same after cloning");
    }

    @Test
    void testDeleteFigureAtInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> collection.deleteAtIndex(-2),
                "IndexOutOfBounds is expected because of negative index or out of bounds");
        assertThrows(IndexOutOfBoundsException.class, () -> collection.deleteAtIndex(15),
                "IndexOutOfBounds is expected because of negative index or out of bounds");
    }

    @Test
    void testDeleteFigureAtValidIndex() {
        int collectionSizeBeforeDeletion = collection.size();
        Figure beforeDeletion = collection.getAt(2);
        Figure afterDeletion = collection.deleteAtIndex(2);
        assertEquals(beforeDeletion, afterDeletion);
        int collectionSizeAfterDeletion = collection.size();
        assertEquals(collectionSizeBeforeDeletion - 1, collectionSizeAfterDeletion,
                "The size should reduce after deletion");
        assertFalse(collection.contains(beforeDeletion),
                "The collection should not contain the figure after deletion");
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
        capture.close();
        String capturedString = testOutput.toString(StandardCharsets.UTF_8);
        assertEquals(capturedString.replaceAll("\\s", ""), printAnswer.replaceAll("\\s", ""));
    }


    @Test
    void testPrintToFile() throws IOException {
        String printAnswer = "triangle 3.5 4.5 5.5\nrectangle 10.0 15.0\ncircle 5.84\ncircle 7.45\n";
        String filepath = "resources/testPrint.txt";
        File testPrintFile = new File(filepath);
        PrintStream fileStream = new PrintStream(testPrintFile);
        collection.print(fileStream);
        fileStream.close();
        StringBuilder content = new StringBuilder();
        List<String> lines = Files.readAllLines(Paths.get(filepath));
        for (String line : lines) {
            content.append(line);
        }
        testPrintFile.delete();
        assertEquals(content.toString().replaceAll("\\s", ""), printAnswer.replaceAll("\\s", ""));
    }

}
