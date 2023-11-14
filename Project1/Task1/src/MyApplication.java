import Factories.AbstractFigureFactory;
import Factories.FigureFactory;
import Figures.Figure;

import java.util.List;
import java.util.Scanner;

public class MyApplication {

    private Scanner scanner;
    private String str;
    private final FigureFactory figureFactory;
    private final int numberOfFigures;
    private List<Figure> collection;

    public MyApplication() {
       Scanner consoleScanner = new Scanner(System.in);
       AbstractFigureFactory abstractFactory = new AbstractFigureFactory();
       figureFactory = abstractFactory.create(scanner.nextLine());
       numberOfFigures = scanner.nextInt();
    }
    public void  readFigures(){

    }
}
