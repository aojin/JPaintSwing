package view.gui;

import controller.GuiObserver;
import model.Point;
import model.StartAndEndPointMode;
import model.interfaces.IShape;
import model.shapes.BoundingBox;
import model.shapes.ShapeList;
import model.persistence.ApplicationState;
import view.interfaces.IPaintCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

// The SUBJECT to our IGuiObserver. VIEW generates events, events typically cause a controller to change a model or view, or both. In this case, the Observer is part of Controller.
// Encapsulates a private class MouseListener which extends MouseAdapter
// MouseEvents are basically catalysts of setChanged()
// the drawing component is a custom observable
// the concrete subject IS-A Component
// make this an observable. Meaning it carries a notify() with it.

// canvas has repaint()...

public class PaintCanvas extends JComponent implements IPaintCanvas{

    private GuiObserver observer;

    // when observer calls update(), concrete subject calls getState().
    // this.repaint() + revalidate() clears....
    private boolean changed = false;

    public PaintCanvas() {
        getGraphics2D(); // when you build your canvas you get a new graphics object.
    }

    public void addGuiObserver(ApplicationState appState) {
        // concrete has an observer
        observer = new GuiObserver(this, appState);
        this.addMouseListener(new MouseListener(observer)); // creates a new MouseListener around our observer.
    }

    public Graphics2D getGraphics2D() { // concrete subject's getState() funct
        // state of what actually displays...
        return (Graphics2D) getGraphics();
    }

    public void clear() {
        this.repaint();
    }

//    public void update(Graphics2D graphics) {
//        this.graphics = graphics;
//        graphicsChanged();
//    }

//    // Observable methods:
//
//    public void graphicsChanged() {
//        setChanged();
//        notifyObserver();
//    }
//
//    public void setChanged() {
//        changed = true;
//    }
//
//    public void notifyObserver(Object arg) {
//        if (changed) {
//            observer.update(this, arg);
//        }
//        changed = false;
//    }
//
//    public void notifyObserver() {
//        notifyObserver(null);
//    }



    //////////////////////////////////////////////////////////////////////////////////////////

    private class MouseListener extends MouseAdapter {

        private GuiObserver observer;
        private ApplicationState appState;
        private model.Point startPoint = new model.Point(0,0);
        private model.Point endPoint = new Point(0,0);

        // drag coordinates for the drag-move function:
        private IShape shapeSelectedToMove = null;

        MouseListener(GuiObserver observer) {
            this.observer = observer;
            this.appState = observer.appState;
        }


        public void mousePressed(MouseEvent e) {
            startPoint.setX(e.getX());
            startPoint.setY(e.getY());

            // MOVE
            if (appState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.MOVE)) {

                shapeSelectedToMove = observer.pickUpShape(startPoint);
            }

            System.out.printf("\nMouse held at point (%d, %d)", startPoint.getX(), startPoint.getY());
        }


        @Override
        public void mouseReleased(MouseEvent e) {
            endPoint.setX(e.getX());
            endPoint.setY(e.getY());
            System.out.printf("\nMouse press released at (%d, %d)", endPoint.getX(), endPoint.getY());

            // DRAW MODE
            if (appState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.DRAW)) {

                observer.buildShape(startPoint, endPoint);

            } else if (appState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.MOVE)) {

                if(shapeSelectedToMove != null) {
                    observer.dropShape(shapeSelectedToMove, endPoint);
                    shapeSelectedToMove = null;
                }

            } else if (appState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.SELECT)){

                BoundingBox selectionBox = new BoundingBox(startPoint, endPoint);

                observer.selectMultipleShapes(selectionBox);

                }
            }
        }
    }



