package model;

import model.interfaces.IIterator;
import model.interfaces.IShape;

import java.util.ArrayList;
import java.util.Iterator;


public class Clipboard implements Iterable<IShape>{
    private ArrayList<IShape> shapes;

    public Clipboard(ArrayList<IShape> shapeList) {
        shapes = shapeList;
    }

    public String toString() {
        String desc = " ";
        for (IShape shape : shapes) {
            desc += shape.printShapeType() + " ";
        }
        return desc;
    }

    @Override
    public Iterator<IShape> iterator() {
        return new ClipboardIterator();
    }

    public class ClipboardIterator implements Iterator<IShape>, IIterator {
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < shapes.size();
        }

        @Override
        public IShape next() {
            return shapes.get(currentIndex++);
        }

        @Override
        public void remove() {
            shapes.remove(--currentIndex);
        }
    }
}