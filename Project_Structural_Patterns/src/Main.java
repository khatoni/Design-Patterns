import bridge.LabelSTDINProxy;
import bridge.SimpleLabel;
import decorators.CompositeTransformation;
import transformations.BracketsTransformation;
import transformations.CensorTransformation;
import bridge.Label;
import transformations.TrimRightTransformation;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Label l1 = new SimpleLabel("albutin maitin tinhu");
        Label l2 = new LabelSTDINProxy(2);
        CompositeTransformation c1 =
            new CompositeTransformation(l2, new BracketsTransformation(), new TrimRightTransformation(),
                new BracketsTransformation(), new CensorTransformation("bu"));
        c1.execute();
    }
}