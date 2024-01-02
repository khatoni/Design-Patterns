package bridge.abstraction;

import bridge.implementations.SimpleLabel;

public abstract class LabelPrinter {
    protected SimpleLabel labelImpl;

    public LabelPrinter(SimpleLabel labelImpl) {
        this.labelImpl = labelImpl;
    }

    public abstract void print();

    public abstract void printWithHelpText();
}
