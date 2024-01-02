package decorators;

import bridge.implementations.Label;
import transformations.TransformationBase;

import java.util.Arrays;

public class CyclingTransformationsDecorator extends LabelDecoratorBase {

    TransformationBase[] transformations;
    int currentToExecute;

    public CyclingTransformationsDecorator(Label label, TransformationBase... transformations) {
        super(label);
        this.transformations = transformations;
        currentToExecute = 0;
    }

    @Override
    public String getText() {
        if (transformations == null) {
            return label.getText();
        }
        TransformationBase currentTransformation = transformations[currentToExecute];
        if (currentToExecute == transformations.length - 1) {
            currentToExecute = 0;
        } else {
            currentToExecute++;
        }
        if (currentTransformation == null) {
            return label.getText();
        }
        return currentTransformation.transform(label.getText());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CyclingTransformationsDecorator that)) {
            return false;
        }

        return Arrays.equals(this.transformations, that.transformations);
    }
}
