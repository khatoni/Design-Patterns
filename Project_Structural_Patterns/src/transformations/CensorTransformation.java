package transformations;

public class CensorTransformation implements TransformationBase {

    private final String wordToCensor;
    private String replacement;

    public CensorTransformation(String wordToCensor) {
        this.wordToCensor = wordToCensor;
        replacement = "";
        for (int i = 0; i < wordToCensor.length(); i++) replacement += "*";
    }

    @Override
    public String transform(String text) {
        return text.replaceAll(wordToCensor, replacement);
    }
}
