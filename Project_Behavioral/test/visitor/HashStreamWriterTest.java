package visitor;

import hash.MD5Sum;
import memento.Memento;
import observer.ConsoleInputObservable;
import observer.Observable;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.AbstractFile;
import structure.File;
import structure.Folder;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HashStreamWriterTest {

    private ByteArrayOutputStream byteArrayOutputStream;

    @BeforeEach
    void prepareOutputStream() {
        byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @AfterEach
    void closeStream() throws IOException {
        byteArrayOutputStream.close();
    }

    @Test
    void testVisitFolder() throws IOException {
        MD5Sum calculator = mock();
        when(calculator.calculate(any())).thenReturn("testHash");

        AbstractFile testFolder = prepareTestFolder();
        HierarchyVisitor writer = new HashStreamWriter(calculator, testFolder, byteArrayOutputStream);
        writer.visitDirectory(testFolder);
        String result = byteArrayOutputStream.toString();
        String expected = "testHash Folder\\File1.txt" + System.lineSeparator() +
            "testHash Folder\\Folder1\\File2.txt" + System.lineSeparator();
        assertEquals(expected, result);
    }

    @Test
    void scanFilesModifyFileAfterStopModification() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintStream pr = new PrintStream(baos)) {
            System.setOut(pr);
            AbstractFile testFolder = prepareTestFolder();
            Visitor writer = new HashStreamWriter(new MD5Sum(), testFolder, byteArrayOutputStream);
            writer.visitFile(testFolder.getFolderContent().get(0));
            ((HashStreamWriter)writer).update(new ConsoleInputObservable(), "Pausing");
            Files.setLastModifiedTime(Path.of(".\\Folder\\File1.txt"), FileTime.fromMillis(System.currentTimeMillis()));
            writer.run();

            String expected = "One of the already scanned file was modified" + System.lineSeparator();
            assertEquals(expected, baos.toString());
        }
    }

    @Test
    void scanFilesModifyFileAfterStopNoModification() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintStream pr = new PrintStream(baos)) {
            System.setOut(pr);
            MD5Sum calc = mock();
            when(calc.calculate(any())).thenReturn("testHash");
            AbstractFile testFolder = prepareTestFolder();
            HashStreamWriter writer = new HashStreamWriter(calc, testFolder, byteArrayOutputStream);
            ((Visitor)writer).visitFile(testFolder.getFolderContent().get(0));
            writer.update(new ConsoleInputObservable(), "Pausing");
            writer.setOutputStream(baos);
            writer.run();

            String expected = "testHash Folder\\Folder1\\File2.txt" + System.lineSeparator();
            assertEquals(expected, baos.toString());
        }
    }

    @Test
    public void testMementoRestoring() throws FileNotFoundException {
        AbstractFile testFolder = prepareTestFolder();
        HashStreamWriter writer = new HashStreamWriter(new MD5Sum(), testFolder, byteArrayOutputStream);
        writer.processFile(testFolder.getFolderContent().get(0));
        HashStreamWriter tmp = writer;
        Memento memento = writer.getMemento();
        writer.processFile(testFolder.getFolderContent().get(1).getFolderContent().get(0));
        writer.restoreState(memento);
        assertEquals(writer, tmp);
    }

    private AbstractFile prepareTestFolder() {
        Folder testFolder = new Folder("Folder", FileTime.fromMillis(System.currentTimeMillis()));
        Folder internalFolder = new Folder("Folder\\Folder1",FileTime.fromMillis(System.currentTimeMillis()));
        File testFile = new File("Folder\\File1.txt", 10000, FileTime.fromMillis(System.currentTimeMillis()));
        File internalFile = new File("Folder\\Folder1\\File2.txt", 1000,FileTime.fromMillis(System.currentTimeMillis()));

        internalFolder.addFolderContent(List.of(internalFile));
        testFolder.addFolderContent(List.of(testFile, internalFolder));
        return testFolder;
    }

    @BeforeAll
    public static void createRealFiles() throws IOException {
        Files.createDirectory(Path.of(".\\Folder"));
        Files.createDirectories(Path.of(".\\Folder\\Folder1"));
        Files.createFile(Path.of(".\\Folder\\File1.txt"));
        Files.createFile(Path.of(".\\Folder\\Folder1\\File2.txt"));
    }

    @AfterAll
    public static void removeRealFiles() throws IOException {
        Files.delete(Path.of(".\\Folder\\Folder1\\File2.txt"));
        Files.delete(Path.of(".\\Folder\\Folder1"));
        Files.delete(Path.of(".\\Folder\\File1.txt"));
        Files.delete(Path.of(".\\Folder"));
    }
}
