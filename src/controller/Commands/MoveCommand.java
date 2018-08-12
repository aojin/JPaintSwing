package controller.Commands;

import controller.GuiObserver;
import model.Point;
import model.interfaces.IShape;
import model.persistence.ICommand;
import model.shapes.ShapeList;

public class MoveCommand implements ICommand {
    GuiObserver observer;
    Point startPoint, endPoint;
    IShape movedShape;
    ShapeList shapeList;



    public MoveCommand(GuiObserver observer, IShape movedShape, Point startPoint, Point endPoint){
        this.observer = observer;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.movedShape = movedShape;
    }

    @Override
    public void undo() {

        shapeList.removeShape(movedShape);
        int width = movedShape.getWidth();
        int height = movedShape.getHeight();

        movedShape.setStartPoint(startPoint.getX(), startPoint.getY());
        movedShape.setEndPoint(startPoint.getX()+width, startPoint.getY()+height);
        shapeList.addShape(movedShape);
        observer.drawShapeList();
    }

    @Override
    public void redo() {
        observer.dropShape(movedShape, endPoint);
    }

    @Override
    public void run() {
        shapeList = observer.getShapeList();
        System.out.printf("\nCreated Move Command starting at %s", startPoint.toString());
        shapeList.removeShape(movedShape);
        observer.dropShape(movedShape, endPoint);

    }
}
