package structure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FolderTest {

    @Test
    void testAddFileToFolder() {

        Folder folder = new Folder("test", null);
        File file = new File("added", 0, null);
        List<AbstractFile> toAdd = List.of(file);
        folder.addFolderContent(toAdd);
        assertTrue(folder.getFolderContent().contains(file), "The file is expected to be added in the folder");
    }

    @Test
    void testAddFolder() {
        Folder externalFolder = new Folder("test", null);
        Folder internalFolder = new Folder("added", null);
        File file = new File("added", 0, null);
        internalFolder.addFolderContent(List.of(file));
        List<AbstractFile> toAdd = List.of(internalFolder);
        externalFolder.addFolderContent(toAdd);
        assertTrue(externalFolder.getFolderContent().contains(internalFolder),
            "The file is expected to be added in the folder");
    }

    @Test
    void testFolderEqualsDifferentTypes() {
        Folder folder = new Folder("test", null);
        AbstractFile file = new File("testfile", 2324, null);
        assertNotEquals(folder, file);
    }

    @Test
    void testFolderAreEqual() {
        Folder externalLHS = new Folder("test", null);
        Folder internalLHS = new Folder("test\\test1", null);
        File internalFileL = new File("test\\test.txt", 234, null);
        File internalFileR = new File("test\\test.txt", 234, null);
        Folder externalRHS = new Folder("test", null);
        Folder internalRHS = new Folder("test\\test1", null);
        externalLHS.addFolderContent(List.of(internalLHS, internalFileL));
        externalRHS.addFolderContent(List.of(internalRHS, internalFileR));
        assertEquals(externalLHS, externalRHS);
    }

    @Test
    void testFolderAreNotEqual() {
        Folder externalLHS = new Folder("test", null);
        Folder internalLHS = new Folder("test\\test1", null);
        File internalFileL = new File("test\\test1.txt", 234, null);
        File internalFileR = new File("test\\test.txt", 234, null);
        Folder externalRHS = new Folder("test", null);
        Folder internalRHS = new Folder("test\\test1", null);
        externalLHS.addFolderContent(List.of(internalLHS, internalFileL));
        externalRHS.addFolderContent(List.of(internalRHS, internalFileR));
        assertNotEquals(externalLHS, externalRHS);
    }
}
