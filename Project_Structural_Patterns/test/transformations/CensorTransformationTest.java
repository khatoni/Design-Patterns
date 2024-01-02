package transformations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CensorTransformationTest {

    @Test
    void testTransformNullWordToCensor() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CensorTransformation(null),
            "IllegalArgumentException was expected");
    }

    @Test
    void testTransformNullString() {
        CensorTransformation toTest = new CensorTransformation("test");
        Assertions.assertThrows(IllegalArgumentException.class, () -> toTest.transform(null),
            "IllegalArgumentException was expected");
    }

    @Test
    void testTransformEmptyString() {
        CensorTransformation toTest = new CensorTransformation("test");
        Assertions.assertEquals("", toTest.transform(""), "The expected result is\"\"");
    }

    @Test
    void testTransformNonEmptyString() {
        CensorTransformation toTest = new CensorTransformation("test");
        Assertions.assertEquals("****ed% or not", toTest.transform("tested% or not"),
            "The expected result is ****ed% or not");
    }
}
