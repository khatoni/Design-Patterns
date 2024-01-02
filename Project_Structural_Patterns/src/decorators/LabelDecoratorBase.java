package decorators;

import bridge.implementations.Label;

public abstract class LabelDecoratorBase implements Label {

    protected Label label;

    public LabelDecoratorBase(Label label) {
        this.label = label;
    }

    public static Label removeDecoratorFrom(Label label, LabelDecoratorBase decoratorType) {
        if (label == null) {
            throw new IllegalArgumentException("Label cannot be null in order to remove a decorator");
        } else if (LabelDecoratorBase.class.isAssignableFrom(label.getClass())) {
            LabelDecoratorBase ldb = (LabelDecoratorBase) label;
            return ldb.removeDecorator(decoratorType);
        } else {
            return label;
        }
    }

    public Label removeDecorator(LabelDecoratorBase decoratorType) {
        if (this.equals(decoratorType)) {
            return label;
        } else if (LabelDecoratorBase.class.isAssignableFrom(label.getClass())) {
            label = ((LabelDecoratorBase) label).removeDecorator(decoratorType);
            return this;
        } else {
            return this;
        }
    }
}
