package transformations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReplaceTransformationTest {

    @Test
    void testTransformNullWordToChange() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ReplaceTransformation(null, "aba"),
            "IllegalArgumentException was expected");
    }

    @Test
    void testTransformNullNewWord() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ReplaceTransformation("old", null),
            "IllegalArgumentException was expected");
    }

    @Test
    void testTransformNullString() {
        ReplaceTransformation toTest = new ReplaceTransformation("", "");
        Assertions.assertThrows(IllegalArgumentException.class, () -> toTest.transform(null),
            "IllegalArgumentException was expected");
    }

    @Test
    void testTransformEmptyString() {
        ReplaceTransformation toTest = new ReplaceTransformation("test1", "test2");
        Assertions.assertEquals("", toTest.transform(""), "The expected result is\"\"");
    }

    @Test
    void testTransformNonEmptyString() {
        ReplaceTransformation toTest = new ReplaceTransformation("test", "design");
        Assertions.assertEquals("designed% or not", toTest.transform("tested% or not"),
            "The expected result is designeded% or not");
    }
}
