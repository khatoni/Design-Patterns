package Factories;

import Figures.Figure;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StreamFigureFactory implements FigureFactory {
    private final Scanner scanner;

    public StreamFigureFactory(Scanner scanner) {

        this.scanner = scanner;
    }


    @Override
    public Figure createFigure() {
        Locale.setDefault(Locale.ENGLISH);
        try {
            String lineOfText = scanner.nextLine();
            return FigureFromString.createFigureFromString(lineOfText);
        } catch (NoSuchElementException | IllegalStateException | IllegalArgumentException e) {
            return null;
        }
    }
}
