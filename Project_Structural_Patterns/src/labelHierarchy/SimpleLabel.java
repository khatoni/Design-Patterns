package labelHierarchy;

public class SimpleLabel implements Label {
    protected String value;
   // protected LabelHierarchy.Label labelImplementation;

    public SimpleLabel() {
        value = null;
    }

    public SimpleLabel(String value) {
        this.value = value;
    }

    public String getText() {
        return value;
    }

  /*  public String getHelperText() {
        return labelImplementation.getHelperText();
    }*/

}
