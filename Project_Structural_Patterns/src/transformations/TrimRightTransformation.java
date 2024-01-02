package transformations;

public class TrimRightTransformation implements TransformationBase {

    @Override
    public String transform(String text) {
        if (text == null) {
            throw new IllegalArgumentException("The argument in the transform function is null");
        }
        return text.stripTrailing();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TrimRightTransformation;
    }
}
