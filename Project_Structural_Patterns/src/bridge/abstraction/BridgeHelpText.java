package bridge.abstraction;

import bridge.implementations.HelpLabel;
import bridge.implementations.Label;

public class BridgeHelpText extends BridgeLabel {

    public BridgeHelpText(Label labelImpl) {
        super(labelImpl);
    }

    @Override
    public String getText() {
        return labelImpl.getText();
    }

    public String getHelperText() {
        if (labelImpl instanceof HelpLabel helpLabel) {
            return helpLabel.getHelperText();
        }
        return "";
    }
}
