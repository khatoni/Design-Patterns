package FigureCollection;

import Figures.Figure;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class FiguresCollection {

    private List<Figure> collection;

    public FiguresCollection() {
        collection = new ArrayList<>();
    }

    public boolean contains(Figure figure) {
        return collection.contains(figure);
    }

    public int size() {
        return collection.size();
    }

    public Figure getAt(int index) {
        if (index >= collection.size() || index < 0) {
            throw new IndexOutOfBoundsException("Index for getAt is out of bounds or negative");
        }
        return collection.get(index);
    }

    public void addFigure(Figure figure) {
        collection.add(figure);
    }

    public Figure clone(int index) {
        if (index >= collection.size() || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return collection.get(index).clone();
    }

    public Figure deleteAtIndex(int index) {
        if (index < 0 || index >= collection.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return collection.remove(index);
    }

    public void print(PrintStream stream) {

        for (Figure figure : collection) {
            stream.println(figure.toString());
        }
    }
}
