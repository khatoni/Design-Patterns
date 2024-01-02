package bridge;

import bridge.abstraction.ConcreteLabelPrinter;
import bridge.abstraction.LabelPrinter;
import bridge.implementations.HelpLabel;
import bridge.implementations.RichLabel;
import bridge.implementations.SimpleLabel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConcreteLabelPrinterTest {

    @Test
    void testGetTextThroughBridgeRichLabel() {
        SimpleLabel toTest = new RichLabel("testRich", 4, 12, "Times New Roman");
        LabelPrinter labelPrinter = new ConcreteLabelPrinter(toTest);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        labelPrinter.print();
        String capturedOutput = outputStream.toString();
        System.setOut(new PrintStream(System.out));
        Assertions.assertEquals("Here is a label: testRich" + System.lineSeparator(), capturedOutput,
            "The expected test was testRich");
    }

    @Test
    void testGetTextThroughBridgeHelpTextLabel() {
        SimpleLabel toTest = new HelpLabel("testHelpLabel", "additional info");
        LabelPrinter labelPrinter = new ConcreteLabelPrinter(toTest);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        labelPrinter.print();
        String capturedOutput = outputStream.toString();
        System.setOut(new PrintStream(System.out));
        Assertions.assertEquals("Here is a label: testHelpLabel" + System.lineSeparator(), capturedOutput,
            "The expected test was testHelpLabel");
    }

    @Test
    void testGetTextThroughBridgeSimpleLabel() {
        SimpleLabel toTest = new SimpleLabel("testSimple");
        LabelPrinter labelPrinter = new ConcreteLabelPrinter(toTest);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        labelPrinter.print();
        String capturedOutput = outputStream.toString();
        System.setOut(new PrintStream(System.out));
        Assertions.assertEquals("Here is a label: testSimple" + System.lineSeparator(), capturedOutput,
            "The expected test was testSimple");
    }

    @Test
    void testGetHelperTextThroughBridgeRichLabel() {
        SimpleLabel toTest = new RichLabel("testRich", 4, 12, "Times New Roman");
        LabelPrinter labelPrinter = new ConcreteLabelPrinter(toTest);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        labelPrinter.printWithHelpText();
        String capturedOutput = outputStream.toString();
        System.setOut(new PrintStream(System.out));
        Assertions.assertEquals(
            "Here is a label: testRich" + System.lineSeparator() + "Some help information about this label: " +
                System.lineSeparator(),
            capturedOutput,
            "The expected result differs from the received");
    }

    @Test
    void testGetHelperTextThroughBridgeHelpTextLabel() {
        SimpleLabel toTest = new HelpLabel("testHelp", "first time testing");
        LabelPrinter labelPrinter = new ConcreteLabelPrinter(toTest);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        labelPrinter.printWithHelpText();
        String capturedOutput = outputStream.toString();
        System.setOut(new PrintStream(System.out));
        Assertions.assertEquals(
            "Here is a label: testHelp" + System.lineSeparator() +
                "Some help information about this label: first time testing" +
                System.lineSeparator(),
            capturedOutput,
            "The expected result differs from the received");
    }

    @Test
    void testGetHelperTextThroughBridgeSimpleLabel() {
        SimpleLabel toTest = new SimpleLabel("testHelp");
        LabelPrinter labelPrinter = new ConcreteLabelPrinter(toTest);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        labelPrinter.printWithHelpText();
        String capturedOutput = outputStream.toString();
        System.setOut(new PrintStream(System.out));
        Assertions.assertEquals(
            "Here is a label: testHelp" + System.lineSeparator() + "Some help information about this label: " +
                System.lineSeparator(),
            capturedOutput,
            "The expected result differs from the received");
    }
}
