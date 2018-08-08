package controller.Commands;

import model.interfaces.IShape;
import model.persistence.ApplicationState;
import model.persistence.ICommand;
import controller.GuiObserver;
import model.persistence.CommandHistory;
import model.shapes.ShapeList;
import view.gui.PaintCanvas;
import view.interfaces.IEventCallback;

import java.awt.*;
import java.util.ArrayList;

public class DeleteCommand implements ICommand {

    ApplicationState applicationState;
    IEventCallback eventCallback;
    private PaintCanvas pc;
    private GuiObserver observer;
    private Graphics2D graphics;
    private final ArrayList allSelectedShapes;
    private final ShapeList shapeList;
    private ArrayList<IShape> tempRemoved = new ArrayList<>();

    public DeleteCommand(GuiObserver observer){
        this.observer = observer;
        allSelectedShapes = observer.getAllSelectedShapes();
        shapeList = observer.getShapeList();
        graphics = observer.getGraphics();
    }

    public void undo() {
        graphics.clearRect(0, 0, pc.getWidth(), pc.getHeight());
        for(IShape deleted: tempRemoved) {
            shapeList.addShape(deleted);
        }
        observer.drawShapeList();
    }


    public void redo() {
        graphics.clearRect(0, 0, pc.getWidth(), pc.getHeight());
        for(IShape deleted: tempRemoved){
            shapeList.removeShape(deleted);
        }
        observer.drawShapeList();
    }

    public void run() {
        if (!allSelectedShapes.isEmpty()){
            CommandHistory.add(this);
        }
        for (int i = 0; i < allSelectedShapes.size(); i++){
            for (IShape shape: shapeList) {
                if(allSelectedShapes.get(i).equals(shape)){
                    shapeList.removeShape(shape);
                    tempRemoved.add(shape);
                    break;
                }
            }
        }
        graphics.clearRect(0,0, pc.getWidth(), pc.getHeight());
        observer.drawShapeList();
    }
}
