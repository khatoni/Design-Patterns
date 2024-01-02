package transformations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrimRightTransformationTest {

    @Test
    void testTransformNullString() {
        TrimRightTransformation toTest = new TrimRightTransformation();
        Assertions.assertThrows(IllegalArgumentException.class, () -> toTest.transform(null),
            "IllegalArgumentException was expected");
    }

    @Test
    void testTransformEmptyString() {
        TrimRightTransformation toTest = new TrimRightTransformation();
        Assertions.assertEquals("", toTest.transform(""), "The expected result is\"\"");
    }

    @Test
    void testTransformNonEmptyStringNoEmptyFromRight() {
        TrimRightTransformation toTest = new TrimRightTransformation();
        Assertions.assertEquals("te               st", toTest.transform("te               st"),
            "The expected result differs from the received one");
    }

    @Test
    void testTransformNonEmptyStringEmptyFromRight() {
        TrimRightTransformation toTest = new TrimRightTransformation();
        Assertions.assertEquals("te      st", toTest.transform("te      st         "),
            "The expected result differs from the received one");
    }
}
