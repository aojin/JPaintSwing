package controller.Commands;

import model.interfaces.IShape;
import model.persistence.ICommand;
import controller.GuiObserver;
import model.shapes.ShapeList;
import java.util.ArrayList;

public class DeleteCommand implements ICommand {

    private GuiObserver observer;
    private final ArrayList allSelectedShapes;
    private final ShapeList shapeList;
    private ArrayList<IShape> tempRemoved = new ArrayList<>();

    public DeleteCommand(GuiObserver observer){
        this.observer = observer;
        allSelectedShapes = observer.getAllSelectedShapes();
        shapeList = observer.getShapeList();
    }

    public void undo() {
        for(IShape deleted: tempRemoved) {
            shapeList.addShape(deleted);
        }
        shapeList.printShapeList();
        observer.drawShapeList();
    }


    public void redo() {
        for(IShape deleted: tempRemoved){
            shapeList.removeShape(deleted);
        }
        shapeList.printShapeList();
        observer.drawShapeList();
    }

    public void run() {
        if (allSelectedShapes != null) {
            tempRemoved.addAll(allSelectedShapes);
            shapeList.delete(allSelectedShapes);
            allSelectedShapes.clear();
        }
        shapeList.printShapeList();
        observer.drawShapeList();
    }
}
