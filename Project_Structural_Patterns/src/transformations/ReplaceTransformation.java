package transformations;

public class ReplaceTransformation implements TransformationBase {

    private String wordToChange;
    private String newWord;

    public ReplaceTransformation(String wordToChange, String newWord) {
        this.wordToChange = wordToChange;
        this.newWord = newWord;
    }

    @Override
    public String transform(String text) {
        return text.replaceAll(wordToChange, newWord);
    }
}
