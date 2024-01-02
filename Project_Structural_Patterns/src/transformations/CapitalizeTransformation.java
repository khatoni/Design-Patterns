package transformations;

public class CapitalizeTransformation implements TransformationBase {

    @Override
    public String transform(String text) {
        if (text == null) {
            throw new IllegalArgumentException("The argument in the transform function is null");
        }
        return text.toUpperCase();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CapitalizeTransformation;
    }
}