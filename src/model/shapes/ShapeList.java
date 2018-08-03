package model.shapes;

import model.interfaces.IIterator;
import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ShapeList implements Iterable<IShape> {
    private ArrayList<IShape> shapeList;

    public ShapeList(ArrayList<IShape> shapeList){ this.shapeList = shapeList; }

    public void addShape(IShape shape){
        shapeList.add(shape);
    }

    public void removeShape(IShape shape) { shapeList.remove(shape); }

    public void drawShape(Graphics2D graphics2D) {
        for(IShape shape : shapeList) {
            shape.draw(graphics2D);
            //shape.drawBoundingBox(graphics2D);
        }
    }

    public int size() {
       return shapeList.size();
    }

    public IShape get(int index) {
        return shapeList.get(index);
    }

    public ShapeIterator iterator() {
        return new ShapeIterator(shapeList);
    }
    // need iterator to remove

    public void printShapeList(){
        Iterator<IShape> iterator = shapeList.iterator();
        System.out.println("\nCurrent ShapeList: ");
        while(iterator.hasNext()){
            System.out.print(iterator.next().printShapeType() + " ");
        }
    }

}
