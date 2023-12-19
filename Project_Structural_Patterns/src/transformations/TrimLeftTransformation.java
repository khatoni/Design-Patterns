package transformations;

public class TrimLeftTransformation implements TransformationBase {

    @Override
    public String transform(String text) {
        return text.stripLeading();
    }
}
