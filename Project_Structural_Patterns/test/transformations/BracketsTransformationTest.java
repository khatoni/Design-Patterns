package transformations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BracketsTransformationTest {

    @Test
    void testTransformNullString() {
        BracketsTransformation toTest = new BracketsTransformation();
        Assertions.assertThrows(IllegalArgumentException.class, () -> toTest.transform(null),
            "IllegalArgumentException was expected");
    }

    @Test
    void testTransformEmptyString() {
        BracketsTransformation toTest = new BracketsTransformation();
        Assertions.assertEquals("{}", toTest.transform(""), "The expected result is {}");
    }

    @Test
    void testTransformNonEmptyString() {
        BracketsTransformation toTest = new BracketsTransformation();
        Assertions.assertEquals("{tested}", toTest.transform("tested"), "The expected result is {tested}");
    }
}
