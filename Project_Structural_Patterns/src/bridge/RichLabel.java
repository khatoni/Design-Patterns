package bridge;

public class RichLabel extends SimpleLabel {
    private final String fontName;
    private final int fontSize;
    int colour;

    public RichLabel(String value, Label labelImplementation, int colour, int fontSize, String fontName) {
        super(value,labelImplementation);
        this.colour = colour;
        this.fontSize = fontSize;
        this.fontName = fontName;
    }


}
