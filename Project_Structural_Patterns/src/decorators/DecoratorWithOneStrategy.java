package decorators;

import bridge.Label;
import transformations.TransformationBase;

public class DecoratorWithOneStrategy extends LabelDecoratorBase {

    private final TransformationBase transformation;

    public DecoratorWithOneStrategy(Label label, TransformationBase transformation) {
        super(label);
        this.transformation = transformation;
    }

    public void execute() {
        System.out.println(transformation.transform(label.getText()));
    }
}
