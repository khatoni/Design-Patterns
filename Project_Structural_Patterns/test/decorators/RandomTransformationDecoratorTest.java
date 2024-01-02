package decorators;

import bridge.implementations.Label;
import bridge.implementations.SimpleLabel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import transformations.BracketsTransformation;
import transformations.CapitalizeTransformation;
import transformations.CensorTransformation;

import java.util.Random;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;

public class RandomTransformationDecoratorTest {

    @Test
    void testRandomTransformationGetTextNullTransformations() {
        Label l = new SimpleLabel("test");
        l = new RandomTransformationDecorator(new Random(), l, null);
        Assertions.assertEquals("test", l.getText(), "The value must be the same with null transformations");
    }

    @Test
    void testRandomTransformationGetTextTransformationsContainNull(){
        Label l = new SimpleLabel("test");
        Random random = mock();
        Mockito.when(random.nextInt(anyInt(), anyInt())).thenReturn(1);
        l = new RandomTransformationDecorator(random, l, new CapitalizeTransformation(), null,
            new BracketsTransformation());
        Assertions.assertEquals("test", l.getText(), "The text must not be changed");
    }

    @Test
    void testRandomTransformationGetTextMockRandomFirstTransformation() {
        Label l = new SimpleLabel("test");
        Random random = mock();
        Mockito.when(random.nextInt(anyInt(), anyInt())).thenReturn(0);
        l = new RandomTransformationDecorator(random, l, new CapitalizeTransformation(), new CensorTransformation("e"),
            new BracketsTransformation());
        Assertions.assertEquals("TEST", l.getText(), "The text was not capitalizes");
    }

    @Test
    void testRandomTransformationGetTextMockRandomLastTransformation() {
        Label l = new SimpleLabel("test");
        Random random = mock();
        Mockito.when(random.nextInt(anyInt(), anyInt())).thenReturn(2);
        l = new RandomTransformationDecorator(random, l, new CapitalizeTransformation(), new CensorTransformation("e"),
            new BracketsTransformation());
        Assertions.assertEquals("{test}", l.getText(), "The text was not with brackets");
    }

    @Test
    void testRandomTransformationGetTextMultipleTransformations() {
        Label l = new SimpleLabel("test");
        Random random = mock();
        Mockito.when(random.nextInt(anyInt(), anyInt())).thenReturn(1, 3, 0);
        l = new RandomTransformationDecorator(random, l, new CapitalizeTransformation(), new CensorTransformation("e"),
            new BracketsTransformation(), new CensorTransformation("t"));
        l = new RandomTransformationDecorator(random, l, new CapitalizeTransformation(), new CensorTransformation("e"),
            new BracketsTransformation(), new CensorTransformation("t"));
        Assertions.assertEquals("**s*", l.getText(), "The expected value is not the same as the expected one");
    }
}
