import Factories.AbstractFigureFactory;
import Factories.FigureFactory;
import FigureCollection.FiguresCollection;
import Figures.Figure;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("How do you want to create figures : random , stdin , file + filepath\n");
        Scanner scanner = new Scanner(System.in);
        String methodOfCreation = scanner.nextLine();
        Integer numberOfFigures = null;
        do {
            System.out.println("Enter how many figures (as a number) do you want to create with this method");
            if (scanner.hasNextInt()) {
                numberOfFigures = scanner.nextInt();
            } else {
                scanner.next();
            }
        } while (numberOfFigures == null);


        FigureFactory factory = AbstractFigureFactory.create(methodOfCreation);
        if (factory == null) {
            System.out.println("Enter valid method of creation and valid existing file");
            return;
        }
        FiguresCollection figures = new FiguresCollection();
        for (int i = 0; i < numberOfFigures; i++) {
            Figure figure = factory.createFigure();
            if (figure != null) {
                figures.addFigure(figure);
            }
        }

        Integer chosenOption = null;

        do {
            System.out.println("""
                    Enter the number value of one of the following options
                    1 - clone a figure at an index\s
                    2 - delete a figure at an index
                    3 - list the figures to stdout
                    4 - store the figures in file
                    5 - exit the program""");
            if (scanner.hasNextInt()) {
                chosenOption = scanner.nextInt();
            } else {
                scanner.next();
                continue;
            }

            switch (chosenOption) {
                case 1 -> {
                    Integer index = null;
                    do {
                        System.out.println("Enter the index");
                        if (scanner.hasNextInt()) {
                            index = scanner.nextInt();
                        } else {
                            scanner.next();
                        }
                    } while (index == null);

                    if (index < 0 || index >= figures.size()) {
                        System.out.println("The index is not valid");
                        return;
                    }
                    Figure cloning = figures.clone(index);
                    figures.addFigure(cloning);
                }
                case 2 -> {
                    Integer index = null;
                    do {
                        System.out.println("Enter the index");
                        if (scanner.hasNextInt()) {
                            index = scanner.nextInt();
                        } else {
                            scanner.next();
                        }
                    } while (index == null);

                    if (index < 0 || index >= figures.size()) {
                        System.out.println("The index is not valid");
                        return;
                    }
                    figures.deleteAtIndex(index);
                }
                case 3 -> figures.print(System.out);
                case 4 -> {
                    String filepath = "";
                    PrintStream fileStream = null;
                    do {
                        System.out.println("Enter the full path of a file where to store the figures:");
                        if (scanner.hasNext()) {
                            filepath = scanner.next();
                        } else {
                            scanner.next();
                        }
                        try {
                            fileStream = new PrintStream(filepath);
                        } catch (FileNotFoundException e) {
                            System.out.println("Enter valid existing file");
                        }
                    }
                    while (fileStream == null);
                    figures.print(fileStream);
                    fileStream.close();
                }
                case 5 -> System.out.println("You exited the program");
            }
        } while (chosenOption == null || chosenOption != 5);

        scanner.close();
    }
}