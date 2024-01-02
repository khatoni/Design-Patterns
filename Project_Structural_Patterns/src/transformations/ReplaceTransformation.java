package transformations;

public class ReplaceTransformation implements TransformationBase {

    private final String wordToChange;
    private final String newWord;

    public ReplaceTransformation(String wordToChange, String newWord) {
        if (wordToChange == null || newWord == null) {
            throw new IllegalArgumentException(
                "One of the arguments in the ReplaceTransformation constructor was null");
        }
        this.wordToChange = wordToChange;
        this.newWord = newWord;
    }

    @Override
    public String transform(String text) {
        if (text == null) {
            throw new IllegalArgumentException("The argument in the transform function is null");
        }
        return text.replaceAll(wordToChange, newWord);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReplaceTransformation that)) {
            return false;
        }

        return that.wordToChange.equals(this.wordToChange) && that.newWord.equals(this.newWord);
    }
}
