package decorators;

import bridge.Label;

public class LabelDecoratorBase implements Label {

    protected Label label;

    public LabelDecoratorBase() {
        label = null;
    }

    public LabelDecoratorBase(Label label) {
        this.label = label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

   /* public static Label removeDecoratorFrom(Label label, Class <? extends LabelDecoratorBase> decoratorType) {
        if(label == null) {
            // Nothing to do
        }

        else if(LabelDecoratorBase.class.isAssignableFrom(label)) {
            // label refers to a decorator. Proceed to remove.
            LabelDecoratorBase ldb = (LabelDecoratorBase)label;
            return ldb.removeDecorator(decoratorType);
        }

        else {
            // Label is not a decorator, but an actual label.
            // Nothing to do in this case
        }
    }

    public Label removeDecorator(Class decoratorType) {

    }
*/
    @Override
    public String getText() {
        return label.getText();
    }
}
