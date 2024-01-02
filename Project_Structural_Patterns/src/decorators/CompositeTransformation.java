package decorators;

import labelHierarchy.Label;
import transformations.TransformationBase;

public class CompositeTransformation extends LabelDecoratorBase {

    private final TransformationBase[] transformations;

    public CompositeTransformation(Label label, TransformationBase... transformations) {
        super(label);
        this.transformations = transformations;
    }

    @Override
    public String getText() {
        String text = label.getText();
        if (transformations == null) {
            return text;
        }
        for (TransformationBase currentTransformation : transformations) {
            text = currentTransformation.transform(text);
        }
        return text;
    }

}
