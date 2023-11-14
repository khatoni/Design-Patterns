package Factories;

import RandomNumberGenerator.RandomGenerator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class AbstractFigureFactory {

    public AbstractFigureFactory() {
    }

    public FigureFactory create(String str) {
        String[] strTokens = str.split(" ");
        switch (strTokens[0]) {
            case "random" -> {
                return new RandomFigureFactory(new RandomGenerator());
            }
            case "file" -> {
                try {
                    return new StreamFigureFactory(new Scanner(new FileReader(strTokens[1])));
                } catch (FileNotFoundException e) {
                    System.out.println("nema file");
                    return null;
                }

            }
            case "stdin" -> {
                Scanner scanner = new Scanner(System.in);
                return new StreamFigureFactory(scanner);
            }
            default -> {
                return null;
            }
        }
    }
}
