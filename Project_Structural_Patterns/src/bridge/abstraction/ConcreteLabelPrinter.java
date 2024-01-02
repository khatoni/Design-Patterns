package bridge.abstraction;

import bridge.implementations.SimpleLabel;

public class ConcreteLabelPrinter extends LabelPrinter {

    public ConcreteLabelPrinter(SimpleLabel labelImpl) {
        super(labelImpl);
    }

    @Override
    public void print() {
        System.out.println("Here is a label: " + labelImpl.getText());
    }

    @Override
    public void printWithHelpText() {
        this.print();
        System.out.println("Some help information about this label: " + labelImpl.getHelperText());
    }
}
