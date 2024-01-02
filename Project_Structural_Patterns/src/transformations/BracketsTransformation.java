package transformations;

public class BracketsTransformation implements TransformationBase {

    @Override
    public String transform(String text) {
        if (text == null) {
            throw new IllegalArgumentException("The argument in the transform function is null");
        }
        return "{" + text + "}";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BracketsTransformation;
    }
}
