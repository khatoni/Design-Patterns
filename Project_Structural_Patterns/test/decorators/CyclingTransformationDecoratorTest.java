package decorators;

import labelHierarchy.Label;
import labelHierarchy.SimpleLabel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import transformations.BracketsTransformation;
import transformations.CapitalizeTransformation;
import transformations.CensorTransformation;

public class CyclingTransformationDecoratorTest {

    @Test
    void testCyclingTransformationGetTextNullTransformations() {
        Label l = new SimpleLabel("test");
        l = new CyclingTransformationsDecorator(l, null);
        Assertions.assertEquals("test", l.getText(), "The expected value is not the same as the received");
    }

    @Test
    void testCyclingTransformationGetTextSuccessfully() {
        Label l = new SimpleLabel("test");
        l = new CyclingTransformationsDecorator(l, new BracketsTransformation(), new CapitalizeTransformation(),
            new CensorTransformation("e"));
        Assertions.assertEquals("{test}", l.getText(), "The expected value is not the same as the received");
        Assertions.assertEquals("TEST", l.getText(), "The expected value is not the same as the received");
        Assertions.assertEquals("t*st", l.getText(), "The expected value is not the same as the received");
        Assertions.assertEquals("{test}", l.getText(), "The expected value is not the same as the received");
    }

    @Test
    void testCyclingTransformationGetTextContainsNullTransformations(){
        Label l = new SimpleLabel("test");
        l = new CyclingTransformationsDecorator(l, new BracketsTransformation(), new CapitalizeTransformation(),
            new CensorTransformation("e"),null);
        Assertions.assertEquals("{test}", l.getText(), "The expected value is not the same as the received");
        Assertions.assertEquals("TEST", l.getText(), "The expected value is not the same as the received");
        Assertions.assertEquals("t*st", l.getText(), "The expected value is not the same as the received");
        Assertions.assertEquals("test", l.getText(), "The expected value is not the same as the received");
        Assertions.assertEquals("{test}", l.getText(), "The expected value is not the same as the received");
    }
}
