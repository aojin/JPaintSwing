package controller.Commands;

import controller.GuiObserver;
import model.Clipboard;
import model.interfaces.IShape;
import model.persistence.ICommand;
import model.shapes.ShapeList;
import model.Point;
import view.gui.PaintCanvas;

import java.awt.*;
import java.util.ArrayList;


public class PasteCommand implements ICommand {

    private Clipboard clipboard;
    private GuiObserver observer;
    private ShapeList shapeList;
    private ShapeList addedShapes = new ShapeList(new ArrayList<>());
    private Graphics2D graphics;
    private PaintCanvas pc;

    public PasteCommand(GuiObserver observer) {
        this.observer = observer;
        this.shapeList = observer.getShapeList();
        this.graphics = observer.getGraphics();
        this.pc = observer.getPaintCanvas();
        this.clipboard = observer.getClipboard();
    }

    @Override
    public void undo() {
        for(IShape shape : addedShapes){
            shapeList.removeShape(shape);
        }
        shapeList.printShapeList();
        observer.drawShapeList();
    }

    @Override
    public void redo() {
        run();
    }

    public void run() {

        if (clipboard != null) {
            for (IShape copiedShape : clipboard) {
                IShape shapeToPaste = observer.duplicateShape(copiedShape); // creates a new shape with the factory which sets all copied fields besides Points

                Point origStartPoint = copiedShape.getStartPoint();
                Point origEndPoint = copiedShape.getEndPoint();

                shapeToPaste.setStartPoint(origStartPoint.getX() + 100, origStartPoint.getY()); // offsets new shape
                shapeToPaste.setEndPoint(origEndPoint.getX() + 100, origEndPoint.getY());

                shapeList.addShape(shapeToPaste); // adds completed new pasted shape
                addedShapes.addShape(shapeToPaste);
            }
            shapeList.printShapeList();
            observer.drawShapeList();
        }

    }
}
