package bridge.implementations;

public class RichLabel extends SimpleLabel {
    private final String fontName;
    private final int fontSize;
    int colour;

    public RichLabel(String value, int colour, int fontSize, String fontName) {
        super(value);
        this.colour = colour;
        this.fontSize = fontSize;
        this.fontName = fontName;
    }
}
