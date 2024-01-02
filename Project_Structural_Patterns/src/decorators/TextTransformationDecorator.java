package decorators;

import bridge.implementations.Label;
import transformations.TransformationBase;

public class TextTransformationDecorator extends LabelDecoratorBase {

    private final TransformationBase transformation;

    public TextTransformationDecorator(Label label, TransformationBase transformation) {
        super(label);
        this.transformation = transformation;
    }

    @Override
    public String getText() {
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
        if (!(obj instanceof TextTransformationDecorator that)) {
            return false;
        }

        return that.transformation.equals(this.transformation);
    }
}
