package controller.Commands;

import controller.GuiObserver;
import model.Point;
import model.interfaces.IShape;
import model.persistence.ICommand;
import model.shapes.ShapeList;

public class DrawCommand implements ICommand {
    GuiObserver observer;
    Point startPoint, endPoint;

    public DrawCommand(GuiObserver observer, Point startPoint, Point endPoint){
        this.observer = observer;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public void undo() {
        ShapeList shapeList = observer.getShapeList();
        if (shapeList.size() >= 1) {
            IShape last = shapeList.get(shapeList.size() - 1); // get last drawn
            shapeList.removeShape(last);
            shapeList.printShapeList();
            observer.drawShapeList();
        } else{
            shapeList.clearList();
            observer.drawShapeList();
        }
    }

    @Override
    public void redo() {
        run();
    }

    @Override
    public void run() {
        observer.buildShape(startPoint, endPoint);
    }
}
