package labelHierarchy;

public class RichLabel extends SimpleLabel {
    private final String fontName;
    private final int fontSize;
    int colour;

    public RichLabel( int colour, int fontSize, String fontName) {

        this.colour = colour;
        this.fontSize = fontSize;
        this.fontName = fontName;
    }


}
