package proxy;

import bridge.implementations.Label;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LabelSTDINProxyTest {

    @Test
    void testGetTextFirstTime() throws IOException {
        Label l = new LabelSTDINProxy(3);
        InputStream inputStream = new ByteArrayInputStream(("first text" + System.lineSeparator()).getBytes());
        System.setIn(inputStream);
        Assertions.assertEquals("first text", l.getText(), "The expected result was first text");
        inputStream.close();
    }

    @Test
    void testGetTextMultipleTimes() throws IOException {
        Label l = new LabelSTDINProxy(4);
        InputStream inputStream = new ByteArrayInputStream(("first text" + System.lineSeparator()).getBytes());
        System.setIn(inputStream);
        String firstCall = l.getText();
        String secondCall = l.getText();
        String thirdCall = l.getText();
        Assertions.assertEquals("first text", thirdCall, "The expected result was first text");
        inputStream.close();
    }

    @Test
    void testGetTextTimeOutOptionKeepTheSameLabel() throws IOException {
        Label l = new LabelSTDINProxy(2);
        InputStream inputStream = new ByteArrayInputStream("first text\nNO\n".getBytes());
        System.setIn(inputStream);
        String firstCall = l.getText();
        String secondCall = l.getText();
        Assertions.assertEquals("first text", firstCall);
        String thirdCall = l.getText();
        Assertions.assertEquals("first text", thirdCall);
        inputStream.close();
    }

    @Test
    void testGetTextTimeOutOptionChangeLabelText() throws IOException {
        Label l = new LabelSTDINProxy(1);
        String input = """
            first text
            YES
            second text
            """;
        InputStream firstStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(firstStream);
        String firstCall = l.getText();
        Assertions.assertEquals("first text", firstCall);
        String secondCall = l.getText();
        Assertions.assertEquals("second text", secondCall);
        firstStream.close();
    }
}
