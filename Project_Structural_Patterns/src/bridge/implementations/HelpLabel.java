package bridge.implementations;

public class HelpLabel extends SimpleLabel {
    private final String helpText;

    public HelpLabel(String text, String helpText) {
        super(text);
        this.helpText = helpText;
    }

    @Override
    public String getHelperText() {
        return helpText;
    }
}
