import Factories.AbstractFigureFactory;
import Factories.FigureFactory;
import FigureCollection.FiguresCollection;
import Figures.Figure;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
/*
        Figure tmp = FigureFromString.createFigureFromString("circle 5.3");
        if(tmp != null){
            System.out.println(tmp.toString());
        }
        else {
            System.out.println("null");
        }
        Figure tmp1 = FigureFromString.createFigureFromString("circle 5tr");
        if(tmp1 != null){
            System.out.println(tmp1.toString());
        }
        else {
            System.out.println("null");
        }
        Figure tmp2 = FigureFromString.createFigureFromString("circle 234");
        if(tmp2 != null){
            System.out.println(tmp2.toString());
        }
        else {
            System.out.println("null");
        }
        Figure rec = FigureFromString.createFigureFromString("rectangle 24.5 18.29");
        System.out.println(rec.toString());
        Figure rec2 = FigureFromString.createFigureFromString("rectangle 24.5");
        if(rec2 != null)System.out.println(rec.toString());
        else {
            System.out.println("null");
        }
        Figure tr = FigureFromString.createFigureFromString("triangle 15.23 11.122 232.23");
        if(tr != null)System.out.println(tr.toString());
        else {r
            System.out.println("null");
        }
*/

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

        FigureFactory factory = new AbstractFigureFactory().create(methodOfCreation);
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
                        System.out.println("Enter how many figures (as a number) do you want to create with this method");
                        if (scanner.hasNextInt()) {
                            index = scanner.nextInt();
                        } else {
                            scanner.next();
                        }
                    } while (index == null);

                    Figure cloning = figures.clone(index);
                    figures.addFigure(cloning);
                }
                case 2 -> {
                    Integer index = null;
                    do {
                        System.out.println("Enter how many figures (as a number) do you want to create with this method");
                        if (scanner.hasNextInt()) {
                            index = scanner.nextInt();
                        } else {
                            scanner.next();
                        }
                    } while (index == null);

                    figures.deleteAtIndex(index);
                }
                case 3 -> figures.print(System.out);
                case 4 -> {
                    String filepath;
                    PrintStream fileStream;
                    while (true) {
                        System.out.println("Enter the full path of a file where to store the figures:\n");
                        try {
                            filepath = scanner.nextLine();
                            fileStream = new PrintStream(filepath);
                            break;
                        } catch (FileNotFoundException | NoSuchElementException | IllegalStateException e) {
                            scanner = new Scanner(System.in);
                        }
                    }
                    figures.print(fileStream);
                    fileStream.close();
                }
                case 5 -> System.out.println("You exited the program");
            }
        } while (chosenOption == null || chosenOption != 5);
    }
}