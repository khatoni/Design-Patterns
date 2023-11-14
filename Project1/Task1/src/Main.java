import Factories.AbstractFigureFactory;
import Figures.Figure;
import Factories.FigureFactory;
import FigureCollection.FiguresCollection;

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
        FiguresCollection collection = new FiguresCollection();
        Scanner consoleScanner = new Scanner(System.in);
        AbstractFigureFactory abstractFactory = new AbstractFigureFactory();
        FigureFactory figureFactory = abstractFactory.create(consoleScanner.nextLine());
        int numberOfFigures = consoleScanner.nextInt();
        System.out.println(numberOfFigures);

        for (int i = 0; i < numberOfFigures; i++) {
            Figure figure = figureFactory.createFigure();
            if (figure != null) {
                collection.addFigure(figure);
            }
        }
        collection.list();
        collection.addFigure(collection.clone(1));
        collection.list();
    }
}