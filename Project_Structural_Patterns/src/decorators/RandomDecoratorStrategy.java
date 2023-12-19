package decorators;

import bridge.Label;
import transformations.TransformationBase;

import java.util.Random;

public class RandomDecoratorStrategy extends LabelDecoratorBase {

    TransformationBase[] transformations;
    Random random;

    public RandomDecoratorStrategy(Random random, Label label, TransformationBase... transformations) {
        super(label);
        this.random = random;
        this.transformations = transformations;
    }

    public void execute() {
        int index = random.nextInt(0, transformations.length);
        TransformationBase transformation = transformations[index];
        transformation.transform(label.getText());
    }
}
