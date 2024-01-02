package transformations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NormalizeSpaceTransformationTest {

    @Test
    void testTransformNullString() {
        NormalizeSpaceTransformation toTest = new NormalizeSpaceTransformation();
        Assertions.assertThrows(IllegalArgumentException.class, () -> toTest.transform(null),
            "IllegalArgumentException was expected");
    }

    @Test
    void testTransformEmptyString() {
        NormalizeSpaceTransformation toTest = new NormalizeSpaceTransformation();
        Assertions.assertEquals("", toTest.transform(""), "The expected result is\"\"");
    }

    @Test
    void testTransformNonEmptyString() {
        NormalizeSpaceTransformation toTest = new NormalizeSpaceTransformation();
        Assertions.assertEquals(" te st ", toTest.transform("   te               st            "),
            "The expected result differs from the received one");
    }
}
