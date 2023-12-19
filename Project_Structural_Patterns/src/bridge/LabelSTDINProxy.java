package bridge;

import bridge.SimpleLabel;

import java.util.Scanner;

public class LabelSTDINProxy extends SimpleLabel {

    private final int timeout;
    private int currentTimeout;

    public LabelSTDINProxy(int timeout) {
        super();
        this.timeout = timeout;
        currentTimeout = 0;
    }

    @Override
    public String getText() {
        if (this.value == null) {
            System.out.println("Its is the first time you ask for the text");
            value = readLabelText();
        } else {
            if (currentTimeout == timeout) {
                changeLabelText();
            }
        }
        currentTimeout++;
        return value;
    }

    private String readLabelText() {
        System.out.println("Enter the label text:");
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        scanner.close();
        return value;
    }

    private void changeLabelText() {
        System.out.println("Do you want to change the label: 1-NO, 2-YES");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.close();
        switch (option) {
            case 1 -> System.out.println("You decided to keep the label text");
            case 2 -> {
                value = readLabelText();
                System.out.println("You changed the label text to " + value);
            }
        }
    }
}
