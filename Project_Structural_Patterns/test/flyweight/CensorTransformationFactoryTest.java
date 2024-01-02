package flyweight;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import transformations.CensorTransformation;

public class CensorTransformationFactoryTest {

    @Test
    void testCreateCensorTransformationNullWord() {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> CensorTransformationFactory.createCensoredWordTransformation(null),
            "IllegalArgumentException was expected");
    }

    @Test
    void testCreateCensorTransformationLessThanFourCharacterWord() {
        CensorTransformation first = CensorTransformationFactory.createCensoredWordTransformation("test");
        CensorTransformationFactory.createCensoredWordTransformation("tes");
        CensorTransformation second = CensorTransformationFactory.createCensoredWordTransformation("test");
        Assertions.assertSame(first, second, "The references must be the same");
    }

    @Test
    void testCreateCensorTransformationLongWord() {
        CensorTransformation expected = new CensorTransformation("testing");
        CensorTransformation test = CensorTransformationFactory.createCensoredWordTransformation("testing");
        Assertions.assertTrue(test instanceof CensorTransformation,
            "The returned object must be instance of CensorTransformation");
        Assertions.assertEquals(test, expected, "The returned object is not the same as the expected one");
    }
}
