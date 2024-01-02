package decorators;

import bridge.implementations.Label;
import bridge.implementations.SimpleLabel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import transformations.BracketsTransformation;
import transformations.CapitalizeTransformation;
import transformations.ReplaceTransformation;

public class CompositeTransformationTest {

    @Test
    void testCompositeTransformationGetTextNullTransformations() {
        Label l = new SimpleLabel("test");
        l = new CompositeTransformation(l, null);
        Assertions.assertEquals("test", l.getText(), "The expected result is text");
    }

    @Test
    void testCompositeTransformationGetTextOneTransformation() {
        Label l = new SimpleLabel("test");
        l = new CompositeTransformation(l, new BracketsTransformation());
        Assertions.assertEquals("{test}", l.getText(), "The expected result is {text}");
    }

    @Test
    void testCompositeTransformationGetTextMultipleTransformations() {
        Label l = new SimpleLabel("test");
        l = new CompositeTransformation(l, new BracketsTransformation(), new ReplaceTransformation("test", "code"),
            new CapitalizeTransformation());
        Assertions.assertEquals("{CODE}", l.getText(), "The expected result is {CODE}");
    }

    @Test
    void testCompositeTransformationGetTextSampleTransformations() {
        Label l = new SimpleLabel("abc_def");
        l = new CompositeTransformation(l, new CapitalizeTransformation(), new BracketsTransformation(),
            new ReplaceTransformation("ABC", "DEF"));
        Assertions.assertEquals("{DEF_DEF}", l.getText(), "The received value is not the same as the expected");
    }
}
