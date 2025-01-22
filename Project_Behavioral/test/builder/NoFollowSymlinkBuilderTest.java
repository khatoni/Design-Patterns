package builder;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import structure.AbstractFile;
import structure.File;
import structure.Folder;
import visitor.HierarchyVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoFollowSymlinkBuilderTest {

    @BeforeAll
    static void prepareTestDirectory() throws IOException {
        Files.createDirectories(Path.of(".\\testing\\testing"));
        Files.createFile(Path.of(".\\testing\\f1.txt"));
        Files.createFile(Path.of(".\\testing\\testing\\f2.txt"));
    }

    @AfterAll
    static void removeTestDirectory() throws IOException {
        Files.delete(Path.of(".\\testing\\testing\\f2.txt"));
        Files.delete(Path.of(".\\testing\\testing"));
        Files.delete(Path.of(".\\testing\\f1.txt"));
        Files.delete(Path.of(".\\testing"));
    }

    @Test
    void testBuildingCorrectStructure() throws IOException {
        DirectoryBuilder directoryBuilder = new NoFollowSymlinkBuilder();
        AbstractFile structure = directoryBuilder.build(".\\testing");
        Folder expectedStructure = new Folder(".\\testing", null);
        Folder internalFolder = new Folder(".\\testing\\testing", null);
        File internalFile = new File(".\\testing\\testing\\f2.txt", 245, null);
        File intextFile = new File(".\\testing\\f1.txt", 24234, null);
        internalFolder.addFolderContent(List.of(internalFile));
        expectedStructure.addFolderContent(List.of(intextFile, internalFolder));
        assertEquals(expectedStructure, structure);
    }
}
