import labelHierarchy.Label;
import labelHierarchy.SimpleLabel;
import decorators.LabelDecoratorBase;
import decorators.TextTransformationDecorator;
import transformations.BracketsTransformation;
import transformations.CapitalizeTransformation;
import transformations.TrimRightTransformation;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        /*LabelHierarchy.Label l1 = new LabelHierarchy.SimpleLabel("albutin maitin tinhu");
        LabelHierarchy.Label l2 = new LabelHierarchy.LabelSTDINProxy(2);
        decorators.CompositeTransformation c1 =
            new decorators.CompositeTransformation(l2, new BracketsTransformation(), new TrimRightTransformation(),
                new BracketsTransformation(), new CensorTransformation("bu"));
        c1.execute();*/
        Label l = new SimpleLabel("hihu ahu              ");
        l = new TextTransformationDecorator(l, new CapitalizeTransformation());
        l = new TextTransformationDecorator(l, new BracketsTransformation());
        l = new TextTransformationDecorator(l, new TrimRightTransformation());
        System.out.println(l.getText());
        LabelDecoratorBase whatToRemove =
            new TextTransformationDecorator(null, new CapitalizeTransformation());
        l = LabelDecoratorBase.removeDecoratorFrom(l, whatToRemove);
        System.out.println(l.getText());
        LabelDecoratorBase whatToRemove2 = new TextTransformationDecorator(null, new BracketsTransformation());
        l = LabelDecoratorBase.removeDecoratorFrom(l,whatToRemove2);
        System.out.println(l.getText());
    }
}