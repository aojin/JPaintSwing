package model.shapes;

import model.interfaces.IIterator;
import model.interfaces.IShape;

import java.util.ArrayList;
import java.util.Iterator;

public class ShapeIterator implements Iterator<IShape>, IIterator {
    private int currentIndex = 0;
    ArrayList<IShape> shapes;

    ShapeIterator(ArrayList<IShape> inShapes) {
        shapes = inShapes;
    }

    @Override
    public boolean hasNext() {
        return shapes.get(currentIndex++) != null;
    }

    @Override
    public IShape next() {
        if (shapes.get(currentIndex++) != null)
            return shapes.get(currentIndex++);
        return null;
    }

    @Override
    public void remove() {
        shapes.remove(currentIndex);
    }
}
