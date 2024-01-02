package labelHierarchy;

import java.util.Scanner;

public class LabelSTDINProxy implements Label {

    private final int timeout;
    private int currentTimeout;
    SimpleLabel labelReference;

    public LabelSTDINProxy(int timeout) {
        this.timeout = timeout;
        currentTimeout = 0;
        labelReference = null;
    }

    @Override
    public String getText() {
        if (labelReference == null) {
            System.out.println("Its is the first time you ask for the text");
            labelReference = new SimpleLabel(readLabelText());
        } else {
            if (currentTimeout == timeout) {
                changeLabelText();
            }
        }
        currentTimeout++;
        return labelReference.getText();
    }

    private String readLabelText() {
        System.out.println("Enter the label text:");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        return value;
    }

    private void changeLabelText() {
        System.out.println("Do you want to change the label: 1-NO, 2-YES");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        switch (option) {
            case "NO" -> System.out.println("You decided to keep the label text");
            case "YES" -> {
                String value = readLabelText();
                labelReference = new SimpleLabel(value);
                System.out.println("You changed the label text to " + labelReference.getText());
            }
        }
    }
}
