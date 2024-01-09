package bridge;

import bridge.abstraction.BridgeHelpText;
import bridge.abstraction.BridgeLabel;
import bridge.implementations.HelpLabel;
import bridge.implementations.Label;
import bridge.implementations.RichLabel;
import bridge.implementations.SimpleLabel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConcreteLabelPrinterTest {

    @Test
    void testGetTextThroughBridgeRichLabel() {
        SimpleLabel toTest = new RichLabel("testRich", 4, 12, "Times New Roman");
        BridgeLabel label = new BridgeHelpText(toTest);
        Assertions.assertEquals("testRich", label.getText(), "The text must be testRich");
    }

    @Test
    void testGetTextThroughBridgeHelpTextLabel() {
        SimpleLabel toTest = new HelpLabel("testHelpLabel", "additional info");
        BridgeHelpText label = new BridgeHelpText(toTest);
        Assertions.assertEquals("testHelpLabel", label.getText(), "The text must be testHelpLabel");
        Assertions.assertEquals("additional info", label.getHelperText(), "The expected text is additional info");

    }

    @Test
    void testGetTextThroughBridgeSimpleLabel() {
        SimpleLabel toTest = new SimpleLabel("testSimple");
        BridgeLabel label = new BridgeHelpText(toTest);
        Assertions.assertEquals("testSimle", label.getText());
    }

    @Test
    void testGetHelperTextThroughBridgeRichLabel() {
        Label toTest = new RichLabel("testRich", 4, 12, "Times New Roman");
        BridgeHelpText label = new BridgeHelpText(toTest);
        Assertions.assertEquals("testRich", label.getHelperText());
    }

    @Test
    void testGetHelperTextThroughBridgeHelpTextLabel() {
        Label toTest = new HelpLabel("testHelp", "first time testing");
        BridgeHelpText label = new BridgeHelpText(toTest);
        Assertions.assertEquals("first time testing", label.getHelperText());
    }

    @Test
    void testGetHelperTextThroughBridgeSimpleLabel() {
        Label toTest = new SimpleLabel("testHelp");
        BridgeHelpText label = new BridgeHelpText(toTest);
        Assertions.assertEquals("", label.getHelperText());
    }
}
