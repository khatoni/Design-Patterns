package transformations;

public class CapitalizeTransformation implements TransformationBase {


    @Override
    public String transform(String text) {
        return text.toUpperCase();
    }
}
