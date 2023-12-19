package bridge;

public class NoHelperTextImpl implements Label {

    @Override
    public String getText() {
        return null;
    }

    @Override
    public String getHelperText() {
        return "";
    }
}
