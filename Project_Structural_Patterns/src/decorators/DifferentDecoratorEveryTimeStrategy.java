package decorators;

import bridge.Label;
import transformations.TransformationBase;

public class DifferentDecoratorEveryTimeStrategy extends LabelDecoratorBase {

    TransformationBase[] transformations;
    int currentToExecute;

    public DifferentDecoratorEveryTimeStrategy(Label label, TransformationBase... transformations) {
        super(label);
        this.transformations = transformations;
        currentToExecute = 0;
    }

    public void execute() {

        TransformationBase currentTransformation = transformations[currentToExecute];
        if (currentToExecute == transformations.length - 1) {
            currentToExecute = 0;
        } else {
            currentToExecute++;
        }
        currentTransformation.transform(label.getText());
    }
}
