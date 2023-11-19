package Factories;

import RandomNumberGenerator.RandomGenerator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class AbstractFigureFactory {

    public static FigureFactory create(String str) {
        String[] strTokens = str.split("\\s");
        switch (strTokens[0]) {
            case "random" -> {
                return new RandomFigureFactory(new RandomGenerator(new Random()));
            }
            case "file" -> {
                try {
                    return new StreamFigureFactory(new Scanner(new FileReader(strTokens[1])));
                } catch (FileNotFoundException e) {
                    System.out.println("no such file");
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
