package decorators;

import bridge.implementations.Label;
import bridge.implementations.SimpleLabel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import transformations.BracketsTransformation;
import transformations.CapitalizeTransformation;
import transformations.CensorTransformation;
import transformations.NormalizeSpaceTransformation;
import transformations.ReplaceTransformation;
import transformations.TrimLeftTransformation;
import transformations.TrimRightTransformation;

public class TextTransformationDecoratorTest {

    @Test
    void testTextTransformationGetTextNullTransformation() {
        Label l = new SimpleLabel("test");
        l = new TextTransformationDecorator(l, null);
        Assertions.assertEquals("test", l.getText(), "The expected value differs from the received");
    }

    @Test
    void testTextTransformationGetTextBracketsTransformation() {
        Label l = new SimpleLabel("{test}");
        l = new TextTransformationDecorator(l, new BracketsTransformation());
        Assertions.assertEquals("{{test}}", l.getText(), "The expected value differs from the received one");
    }

    @Test
    void testTextTransformationGetTextMultipleBracketsTransformation() {
        Label l = new SimpleLabel("test");
        l = new TextTransformationDecorator(l, new BracketsTransformation());
        l = new TextTransformationDecorator(l, new BracketsTransformation());
        l = new TextTransformationDecorator(l, new BracketsTransformation());
        Assertions.assertEquals("{{{test}}}", l.getText(), "The expected value differs from the received one");
    }

    @Test
    void testTextTransformationGetTextCapitalizeTransformation() {
        Label l = new SimpleLabel("test");
        l = new TextTransformationDecorator(l, new CapitalizeTransformation());
        Assertions.assertEquals("TEST", l.getText(), "The expected value is not capitalized");
    }

    @Test
    void testTextTransformationGetTextCensorTransformation() {
        Label l = new SimpleLabel("The text, which is about to be censored");
        l = new TextTransformationDecorator(l, new CensorTransformation("text"));
        Assertions.assertEquals("The ****, which is about to be censored", l.getText(),
            "The expected value differs, from the received one");
    }

    @Test
    void testTextTransformationGetTextMultipleCensorTransformation() {
        Label l = new SimpleLabel("The text, which is about to be censored");
        l = new TextTransformationDecorator(l, new CensorTransformation("text"));
        l = new TextTransformationDecorator(l, new CensorTransformation("censor"));
        l = new TextTransformationDecorator(l, new CensorTransformation("t"));
        Assertions.assertEquals("The ****, which is abou* *o be ******ed", l.getText(),
            "The expected value differs, from the received one");
    }

    @Test
    void testTextTransformationGetTextNormalizeSpaceTransformation() {
        Label l = new SimpleLabel("t           s");
        l = new TextTransformationDecorator(l, new NormalizeSpaceTransformation());
        Assertions.assertEquals("t s", l.getText(), "The received value is not space-normalized");
    }

    @Test
    void testTextTransformationGetTextReplaceTransformation() {
        Label l = new SimpleLabel("The text, which is about to be replaced");
        l = new TextTransformationDecorator(l, new ReplaceTransformation("text", "label"));
        Assertions.assertEquals("The label, which is about to be replaced", l.getText(),
            "The received value is not the same as the expected one");
    }

    @Test
    void testTextTransformationGetTextMultipleReplaceTransformation() {
        Label l = new SimpleLabel("The text, which is about to be replaced");
        l = new TextTransformationDecorator(l, new ReplaceTransformation("text", "label"));
        l = new TextTransformationDecorator(l, new ReplaceTransformation("label", "design"));
        l = new TextTransformationDecorator(l, new ReplaceTransformation("replaced", "created"));
        Assertions.assertEquals("The design, which is about to be created", l.getText(),
            "The received value is not the same as the expected one");
    }

    @Test
    void testTextTransformationGetTextTrimLeftTransformation() {
        Label l = new SimpleLabel("       text");
        l = new TextTransformationDecorator(l, new TrimLeftTransformation());
        Assertions.assertEquals("text", l.getText(),
            "The received value is not the same as the expected one");
    }

    @Test
    void testTextTransformationGetTextTrimRightTransformation() {
        Label l = new SimpleLabel("text          ");
        l = new TextTransformationDecorator(l, new TrimRightTransformation());
        Assertions.assertEquals("text", l.getText(),
            "The received value is not the same as the expected one");
    }

    @Test
    void testTextTransformationGetTextMixedTransformations() {
        Label l = new SimpleLabel("   text        to change       ");
        l = new TextTransformationDecorator(l, new NormalizeSpaceTransformation());
        l = new TextTransformationDecorator(l, new TrimLeftTransformation());
        l = new TextTransformationDecorator(l, new TrimRightTransformation());
        l = new TextTransformationDecorator(l, new BracketsTransformation());
        l = new TextTransformationDecorator(l, new BracketsTransformation());
        l = new TextTransformationDecorator(l, new ReplaceTransformation("text", "label"));
        l = new TextTransformationDecorator(l, new ReplaceTransformation("label", "field"));
        l = new TextTransformationDecorator(l, new CapitalizeTransformation());
        l = new TextTransformationDecorator(l, new CensorTransformation("CHANGE"));
        Assertions.assertEquals("{{FIELD TO ******}}", l.getText(),
            "The received value is not the same as the expected one");
    }
}
