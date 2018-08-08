package model.shapes;

import model.interfaces.IIterator;
import model.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ShapeList implements Iterable<IShape> {
    private ArrayList<IShape> shapeList;

    public ShapeList(ArrayList<IShape> shapeList){ this.shapeList = shapeList; }

    protected ShapeList() {
    }

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

    public void clearList(){
        Iterator<IShape> iterator = shapeList.iterator();
        while(iterator.hasNext()){
            IShape next = iterator.next();
            iterator.remove();
        }
    }

    public ShapeList delete(ArrayList<IShape> toDelete) {
        shapeList.removeIf(toDelete::contains);
        return this;
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
        System.out.println("\n\nCurrent ShapeList stored in Model: \n");
        System.out.print("{ ");
        while(iterator.hasNext()){
            System.out.print("[" + iterator.next().printShapeType() + "] , ");
        }
        System.out.print("}\n");
    }

}
