package decorators;

import bridge.Label;
import transformations.TransformationBase;

public class CompositeTransformation extends LabelDecoratorBase {

    private final TransformationBase[] transformations;

    public CompositeTransformation(Label label, TransformationBase... transformations) {
        super(label);
        this.transformations = transformations;
    }

    public void execute() {
        String text = label.getText();
        for (TransformationBase currentTransformation : transformations) {
            text = currentTransformation.transform(text);
        }
        System.out.println(text);
    }

}
