package transformations;

public class TrimRightTransformation implements TransformationBase {

    @Override
    public String transform(String text) {
        return text.stripTrailing();
    }
}
