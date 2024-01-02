package bridge.implementations;

public class SimpleLabel implements Label {
    protected String value;

    public SimpleLabel(String value) {
        this.value = value;
    }

    public String getText() {
        return value;
    }

    public String getHelperText() {
        return "";
    }
}
