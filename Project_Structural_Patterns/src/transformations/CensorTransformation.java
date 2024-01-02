package transformations;

public class CensorTransformation implements TransformationBase {

    private final String wordToCensor;
    private String replacementWord;

    public CensorTransformation(String wordToCensor) {
        if (wordToCensor == null) {
            throw new IllegalArgumentException("The argument wordToCensor in the constructor is null");
        }
        this.wordToCensor = wordToCensor;
        replacementWord = "";
        for (int i = 0; i < wordToCensor.length(); i++) replacementWord += "*";
    }

    @Override
    public String transform(String text) {
        if (text == null) {
            throw new IllegalArgumentException("The argument in the transform function is null");
        }
        return text.replaceAll(wordToCensor, replacementWord);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CensorTransformation that)) {
            return false;
        }

        return that.wordToCensor.equals(this.wordToCensor);
    }
}
