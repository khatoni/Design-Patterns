package builder;

import org.junit.jupiter.api.Test;
import structure.AbstractFile;
import structure.File;
import structure.Folder;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FollowSymLinkBuilderTest {

    @Test
    void testBuildingCorrectStructure() throws IOException {
        DirectoryBuilder directoryBuilder = new FollowSymLinkBuilder();
        AbstractFile structure = directoryBuilder.build(".\\symlink_test");
        Folder expectedStructure = new Folder(".\\symlink_test", null);
        Folder internalFolder = new Folder(".\\symlink_test\\test", null);
        File internalFile = new File(".\\symlink_test\\test\\test.txt", 245, null);
        File intextFile = new File(".\\symlink_test\\test.txt", 24234, null);
        internalFolder.addFolderContent(List.of(internalFile));
        expectedStructure.addFolderContent(List.of(intextFile, internalFolder));
        assertEquals(expectedStructure, structure);
    }
}
