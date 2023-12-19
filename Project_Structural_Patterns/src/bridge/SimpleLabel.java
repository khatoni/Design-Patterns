package bridge;

public class SimpleLabel {
    protected String value;
    protected Label labelImplementation;

    public SimpleLabel() {
        value = null;
    }

    public SimpleLabel(String value, Label labelImplementation) {
        this.value = value;
        this.labelImplementation = labelImplementation;
    }

    public String getText() {
        return labelImplementation.getText();
    }

    public String getHelperText() {
        return labelImplementation.getHelperText();
    }

}
