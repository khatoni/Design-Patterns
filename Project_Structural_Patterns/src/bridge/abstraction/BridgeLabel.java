package bridge.abstraction;

import bridge.implementations.Label;
import bridge.implementations.SimpleLabel;

public abstract class BridgeLabel {
    protected Label labelImpl;

    public BridgeLabel(Label labelImpl) {

        this.labelImpl = labelImpl;
    }

    public abstract String getText();

}
