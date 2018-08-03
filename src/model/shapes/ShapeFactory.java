package model.shapes;

import model.interfaces.IShape;


public class ShapeFactory {

    //use getShape to get the object
    public IShape getShape(ShapeType shapeType){

        IShape shape = null;
        switch (shapeType){
            case ELLIPSE:
                shape = new Ellipse();
                break;
            case TRIANGLE:
                shape = new Triangle();
                break;
            case RECTANGLE:
                shape = new Rectangle();
                break;
        }
        return shape;
    }
}