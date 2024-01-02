package decorators;

import labelHierarchy.Label;
import labelHierarchy.SimpleLabel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import transformations.BracketsTransformation;
import transformations.CapitalizeTransformation;
import transformations.CensorTransformation;
import transformations.NormalizeSpaceTransformation;
import transformations.ReplaceTransformation;
import transformations.TrimLeftTransformation;
import transformations.TrimRightTransformation;

import java.util.Random;

import static org.mockito.ArgumentMatchers.anyInt;

public class LabelDecoratorBaseTest {

    @Test
    void testRemoveDecoratorFromNullLabel() {
        Label l = null;
        LabelDecoratorBase whatToRemove = new TextTransformationDecorator(null, new CensorTransformation("t"));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> LabelDecoratorBase.removeDecoratorFrom(l, whatToRemove), "IllegalArgumentException was expected");
    }

    @Test
    void testRemoveDecoratorFromLabel() {
        Label l = new SimpleLabel("test");
        LabelDecoratorBase whatToRemove = new TextTransformationDecorator(null, new BracketsTransformation());
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemove);
        Assertions.assertEquals("test", l.getText(), "The expected result is just the label text");
    }

    @Test
    void testRemoveDecoratorFromLabelOneTextTransformation() {
        Label l = new SimpleLabel("test");
        l = new TextTransformationDecorator(l, new CapitalizeTransformation());
        LabelDecoratorBase whatToRemove = new TextTransformationDecorator(null, new CapitalizeTransformation());
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemove);
        Assertions.assertEquals("test", l.getText(), "The expected result is just the label text");
    }

    @Test
    void testRemoveDecoratorFromLabelMultipleTextTransformation() {
        Label l = new SimpleLabel("test");
        l = new TextTransformationDecorator(l, new CapitalizeTransformation());
        l = new TextTransformationDecorator(l, new BracketsTransformation());
        l = new TextTransformationDecorator(l, new CensorTransformation("ES"));
        LabelDecoratorBase whatToRemove = new TextTransformationDecorator(null, new BracketsTransformation());
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemove);
        Assertions.assertEquals("T**T", l.getText(), "The expected result was T**T, but was different");
    }

    @Test
    void testRemoveDecoratorFromLabelMultipleRemoveTextTransformations() {
        Label l = new SimpleLabel("test");
        l = new TextTransformationDecorator(l, new CapitalizeTransformation());
        l = new TextTransformationDecorator(l, new BracketsTransformation());
        l = new TextTransformationDecorator(l, new BracketsTransformation());
        l = new TextTransformationDecorator(l, new ReplaceTransformation("ES", "SE"));
        LabelDecoratorBase whatToRemove = new TextTransformationDecorator(null, new BracketsTransformation());
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemove);
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemove);
        Assertions.assertEquals("TSET", l.getText(), "The expected result was TSET, but was different");
    }

    @Test
    void testRemoveDecoratorFromLabelMultipleRemoveTextTransformationsCensorTransformations() {
        Label l = new SimpleLabel("test");
        l = new TextTransformationDecorator(l, new CapitalizeTransformation());
        l = new TextTransformationDecorator(l, new BracketsTransformation());
        l = new TextTransformationDecorator(l, new TrimLeftTransformation());
        l = new TextTransformationDecorator(l, new CensorTransformation("T"));
        l = new TextTransformationDecorator(l, new CensorTransformation("}"));
        LabelDecoratorBase whatToRemoveReplace =
            new TextTransformationDecorator(null, new ReplaceTransformation("a", "b"));
        LabelDecoratorBase whatToRemove = new TextTransformationDecorator(null, new CensorTransformation("}"));
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemove);
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemoveReplace);
        Assertions.assertEquals("{*ES*}", l.getText(), "The expected result was {*ES*}, but was different");
    }

    @Test
    void testRemoveDecoratorFromLabelMultipleDifferentRemoveTextTransformations() {
        Label l = new SimpleLabel("te  st");
        l = new TextTransformationDecorator(l, new CapitalizeTransformation());
        l = new TextTransformationDecorator(l, new BracketsTransformation());
        l = new TextTransformationDecorator(l, new BracketsTransformation());
        l = new TextTransformationDecorator(l, new TrimRightTransformation());
        l = new TextTransformationDecorator(l, new ReplaceTransformation("E", "SE"));
        LabelDecoratorBase whatToRemoveBrackets = new TextTransformationDecorator(null, new BracketsTransformation());
        LabelDecoratorBase whatToRemoveLeftSpaces =
            new TextTransformationDecorator(null, new TrimLeftTransformation());
        LabelDecoratorBase whatToRemoveRightSpaces =
            new TextTransformationDecorator(null, new TrimRightTransformation());
        LabelDecoratorBase whatToRemoveNormalize =
            new TextTransformationDecorator(null, new NormalizeSpaceTransformation());
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemoveBrackets);
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemoveLeftSpaces);
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemoveRightSpaces);
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemoveNormalize);
        Assertions.assertEquals("{TSE  ST}", l.getText(), "The expected result differs from the received one");
    }

    @Test
    void testRemoveDecoratorFromLabelOneRandomTransformation() {
        Label l = new SimpleLabel("test");
        l = new RandomTransformationDecorator(new Random(), l, new CapitalizeTransformation(),
            new BracketsTransformation(),
            new CensorTransformation("s"));
        LabelDecoratorBase whatToRemove =
            new RandomTransformationDecorator(new Random(), null, new CapitalizeTransformation(),
                new BracketsTransformation(), new CensorTransformation("s"));
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemove);
        Assertions.assertEquals("test", l.getText(), "The expected text is test");
    }

    @Test
    void testRemoveDecoratorFromLabelMultipleRandomTransformation() {
        Label l = new SimpleLabel("test");
        l = new RandomTransformationDecorator(new Random(), l, new CapitalizeTransformation(),
            new BracketsTransformation(),
            new CensorTransformation("s"));
        Random random = Mockito.mock();
        Mockito.when(random.nextInt(anyInt(), anyInt())).thenReturn(0);
        l = new RandomTransformationDecorator(random, l, new BracketsTransformation(),
            new BracketsTransformation(),
            new CensorTransformation("t"));
        LabelDecoratorBase whatToRemove =
            new RandomTransformationDecorator(new Random(), null, new CapitalizeTransformation(),
                new BracketsTransformation(), new CensorTransformation("s"));
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemove);
        Assertions.assertEquals("{test}", l.getText(), "The expected text is {test}");
    }

    @Test
    void testRemoveDecoratorFromLabelOneCyclingTransformation() {
        Label l = new SimpleLabel("test");
        l = new CyclingTransformationsDecorator(l, new CapitalizeTransformation(),
            new BracketsTransformation(),
            new CensorTransformation("s"));
        LabelDecoratorBase whatToRemove =
            new CyclingTransformationsDecorator(null, new CapitalizeTransformation(),
                new BracketsTransformation(), new CensorTransformation("s"));
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemove);
        Assertions.assertEquals("test", l.getText(), "The expected text is test");
    }

    @Test
    void testRemoveDecoratorFromLabelMultipleCyclingTransformations() {
        Label l = new SimpleLabel("test");
        l = new CyclingTransformationsDecorator(l, new CapitalizeTransformation(),
            new BracketsTransformation(),
            new CensorTransformation("s"));

        l = new CyclingTransformationsDecorator(l, new BracketsTransformation(),
            new BracketsTransformation(),
            new CensorTransformation("t"));
        LabelDecoratorBase whatToRemove =
            new CyclingTransformationsDecorator(null, new CapitalizeTransformation(),
                new BracketsTransformation(), new CensorTransformation("s"));
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemove);
        Assertions.assertEquals("{test}", l.getText(), "The expected text is {test}");
    }

    @Test
    void testRemoveDecoratorFromLabelDifferentDecorators() {
        Label l = new SimpleLabel("test");
        l = new TextTransformationDecorator(l, new CapitalizeTransformation());
        l = new RandomTransformationDecorator(new Random(), l, new BracketsTransformation(),
            new BracketsTransformation());
        l = new CyclingTransformationsDecorator(l, new CensorTransformation("E"),
            new ReplaceTransformation("*", "a"));
        LabelDecoratorBase whatToRemove =
            new RandomTransformationDecorator(new Random(), null, new BracketsTransformation(),
                new BracketsTransformation());
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemove);
        Assertions.assertEquals("T*ST", l.getText(), "The expected text is t*st");
    }
}
