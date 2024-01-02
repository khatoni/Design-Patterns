package transformations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CapitalizeTransformationTest {

    @Test
    void testTransformNullString() {
        CapitalizeTransformation toTest = new CapitalizeTransformation();
        Assertions.assertThrows(IllegalArgumentException.class, () -> toTest.transform(null),
            "IllegalArgumentException was expected");
    }

    @Test
    void testTransformEmptyString() {
        CapitalizeTransformation toTest = new CapitalizeTransformation();
        Assertions.assertEquals("", toTest.transform(""), "The expected result is\"\"");
    }

    @Test
    void testTransformNonEmptyString() {
        CapitalizeTransformation toTest = new CapitalizeTransformation();
        Assertions.assertEquals("TESTED%", toTest.transform("tested%"), "The expected result is TESTED");
    }
}
