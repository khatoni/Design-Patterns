package transformations;

public class NormalizeSpaceTransformation implements TransformationBase {


    @Override
    public String transform(String text) {
        return text.replaceAll("\\s+", " ");
    }
}
