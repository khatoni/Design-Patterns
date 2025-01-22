package visitor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structure.AbstractFile;
import structure.File;
import structure.Folder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.attribute.FileTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportWriterTest {

    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream pr;

    @BeforeEach
    void prepareOutputStream() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        pr = new PrintStream(byteArrayOutputStream);
        System.setOut(pr);
    }

    @AfterEach
    void closeStream() {
        pr.close();
    }

    @Test
    void testProcessFile() {
        ReportWriter reportWriter = new ReportWriter(null);
        AbstractFile testFile = new File("File1.txt", 189123.0, FileTime.fromMillis(1000000));
        reportWriter.processFile(testFile);
        String result = byteArrayOutputStream.toString();
        assertEquals("FileName: File1.txt FileSize: 189123.0" + System.lineSeparator(), result);
    }

    @Test
    void testVisitFile() {
        ReportWriter reportWriter = new ReportWriter(null);
        AbstractFile testFile = new File("File1.txt", 189123.0, FileTime.fromMillis(1000000));
        reportWriter.visitFile(testFile);
        String result = byteArrayOutputStream.toString();
        assertEquals("FileName: File1.txt FileSize: 189123.0" + System.lineSeparator(), result);
    }

    @Test
    void testVisitFolder() {
        AbstractFile testFolder = prepareTestFolder();
        ReportWriter reportWriter = new ReportWriter(testFolder);
        reportWriter.visitDirectory(testFolder);
        String expected = "FileName: Folder\\File1.txt FileSize: 10000.0" + System.lineSeparator() +
            "FileName: Folder\\Folder1\\File2.txt FileSize: 1000.0" + System.lineSeparator();
        String captured = byteArrayOutputStream.toString();
        assertEquals(expected, captured);
    }

    @Test
    void testScanFolder() {
        AbstractFile testFolder = prepareTestFolder();
        ReportWriter reportWriter = new ReportWriter(testFolder);
        reportWriter.run();
        String expected = "FileName: Folder\\File1.txt FileSize: 10000.0" + System.lineSeparator() +
            "FileName: Folder\\Folder1\\File2.txt FileSize: 1000.0" + System.lineSeparator();
        String captured = byteArrayOutputStream.toString();
        assertEquals(expected, captured);

    }

    private AbstractFile prepareTestFolder() {
        Folder testFolder = new Folder("Folder", FileTime.fromMillis(1000000));
        Folder internalFolder = new Folder("Folder\\Folder1", FileTime.fromMillis(1000000));
        File testFile = new File("Folder\\File1.txt", 10000, FileTime.fromMillis(1000000));
        File internalFile = new File("Folder\\Folder1\\File2.txt", 1000, FileTime.fromMillis(1000000));
        internalFolder.addFolderContent(List.of(internalFile));
        testFolder.addFolderContent(List.of(testFile, internalFolder));
        return testFolder;
    }
}
