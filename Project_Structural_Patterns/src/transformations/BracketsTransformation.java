package transformations;

public class BracketsTransformation implements TransformationBase {

    @Override
    public String transform(String text) {
        return "{" + text + "}";
    }
}
