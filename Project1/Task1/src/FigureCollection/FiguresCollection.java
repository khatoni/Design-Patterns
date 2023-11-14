package FigureCollection;

import Figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class FiguresCollection {

    private final List<Figure> collection;

    public FiguresCollection() {
        collection = new ArrayList<>();
    }

    public void addFigure(Figure figure) {
        collection.add(figure);
    }

    public Figure clone(int index) {
        if (index >= collection.size()) {
            throw new IllegalArgumentException("out of bounds");
        }
        return collection.get(index).clone();
    }

    public void print() {
        int index = 0;
        for (Figure figure : collection) {
            System.out.printf("%s %d%n", figure.toString(), index);
            index++;
        }
    }
}
