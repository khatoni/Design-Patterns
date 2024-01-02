package transformations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrimLeftTransformationTest {

    @Test
    void testTransformNullString() {
        TrimLeftTransformation toTest = new TrimLeftTransformation();
        Assertions.assertThrows(IllegalArgumentException.class, () -> toTest.transform(null),
            "IllegalArgumentException was expected");
    }

    @Test
    void testTransformEmptyString() {
        TrimLeftTransformation toTest = new TrimLeftTransformation();
        Assertions.assertEquals("", toTest.transform(""), "The expected result is\"\"");
    }

    @Test
    void testTransformNonEmptyStringNoEmptyFromLeft() {
        TrimLeftTransformation toTest = new TrimLeftTransformation();
        Assertions.assertEquals("te               st ", toTest.transform("te               st "),
            "The expected result differs from the received one");
    }

    @Test
    void testTransformNonEmptyStringEmptyFromLeft() {
        TrimLeftTransformation toTest = new TrimLeftTransformation();
        Assertions.assertEquals("te      st ", toTest.transform("       te      st "),
            "The expected result differs from the received one");
    }
}
