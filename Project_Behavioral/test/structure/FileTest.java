package structure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileTest {

    @Test
    void testFileEqualsTheSameObject() {
        AbstractFile file = new File("test", 25020, null);
        assertEquals(file, file);
    }

    @Test
    void testFileEqualsDifferentTypes() {
        AbstractFile file = new File("test", 1231, null);
        assertNotEquals(file, new Folder("testfolder", null));
    }

    @Test
    void testFileEqualsCorrect() {
        AbstractFile lhs = new File("test", 1231, null);
        AbstractFile rhs = new File("test", 24234, null);
        assertEquals(lhs, rhs);
    }

    @Test
    void getFolderContentException() {
        assertThrows(UnsupportedOperationException.class, () -> new File("test", 2432, null).getFolderContent());
    }

}
