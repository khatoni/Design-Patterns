package transformations;

public class NormalizeSpaceTransformation implements TransformationBase {

    @Override
    public String transform(String text) {
        if (text == null) {
            throw new IllegalArgumentException("The argument in the transform function is null");
        }
        return text.replaceAll("\\s+", " ");
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NormalizeSpaceTransformation;
    }
}
