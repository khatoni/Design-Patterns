package decorators;

import bridge.implementations.Label;
import transformations.TransformationBase;

import java.util.Arrays;
import java.util.Random;

public class RandomTransformationDecorator extends LabelDecoratorBase {

    TransformationBase[] transformations;
    Random random;
    int previousIndex;

    public RandomTransformationDecorator(Random random, Label label, TransformationBase... transformations) {
        super(label);
        this.random = random;
        this.transformations = transformations;
        this.previousIndex = -1;
    }

    @Override
    public String getText() {
        if (transformations == null) {
            return label.getText();
        }
        int index = -1;
        while (previousIndex == index) {
            index = random.nextInt(0, transformations.length);
        }
        previousIndex = index;
        TransformationBase transformation = transformations[index];
        if (transformation == null) {
            return label.getText();
        }
        return transformation.transform(label.getText());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RandomTransformationDecorator that)) {
            return false;
        }

        return Arrays.equals(this.transformations, that.transformations);
    }
}
