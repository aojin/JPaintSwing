package controller;

import model.Point;
import model.persistence.ApplicationState;
import model.shapes.*;
import model.interfaces.IShape;
import view.gui.PaintCanvas;


import java.awt.*;
import java.util.ArrayList;


public class GuiObserver {

    // the observer is like the mvc pivot point in the controller.

    public ApplicationState appState;
    private ShapeList shapeList = new ShapeList(new ArrayList<>());
    private Graphics2D graphics;
    private PaintCanvas pc;
    private final ShapeFactory shapeFactory = new ShapeFactory();
   // private final CommandFactory commandFactory = new CommandFactory(this);
    private ArrayList<IShape> allSelectedShapes = new ArrayList<>();

    public GuiObserver(PaintCanvas pc, ApplicationState appState) {
        setGraphics(pc.getGraphics2D());
        this.pc = pc;
        this.appState = appState;
    }

    private void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void buildShape(Point startPoint, Point endPoint) {

        //the canvas calls observer methods....

        // the observer gets the relevant data from AppState and keeps track of everything.

        IShape shape = shapeFactory.getShape(appState.getActiveShapeType());
        shape.setStartPoint(startPoint.getX(), startPoint.getY());
        shape.setEndPoint(endPoint.getX(), endPoint.getY());
        shape.setShapeShadingType(appState.getActiveShapeShadingType());
        shape.setPrimaryColor(appState.getActivePrimaryColor());
        if (appState.getActiveShapeShadingType().equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
            shape.setSecondaryColor(appState.getActiveSecondaryColor());
        }
        shapeList.addShape(shape);

        System.out.printf("\nAdded %s to the shapeList.", shape.printShapeType());
        shapeList.printShapeList(); // Uses iterator to print a list of shapeTypes to console
        drawShapeList();
    }

    private void drawShapeList() {
        shapeList.drawShape(graphics);
    }

    private boolean shapeContainedInSelectedShapes(IShape shape) { // check membership
        return allSelectedShapes.contains(shape);
    }

    private void setShapeAsSelected(IShape shapeToSelect) {
        if (!shapeContainedInSelectedShapes(shapeToSelect))
            shapeToSelect.setSelected(true);
            allSelectedShapes.add(shapeToSelect);
    }

    private void printSelectedShapesList() {
        System.out.println("\nSelected Shapes List: ");
        for (IShape shape : allSelectedShapes) {
            System.out.println(shape.toString());
        }
    }


    public void selectMultipleShapes(BoundingBox selectionBox) {

        allSelectedShapes.clear();

        for (int i = shapeList.size() - 1; i >= 0; i-- ) {
            IShape shape = shapeList.get(i);
            BoundingBox shapeBound = shape.getBoundingBox();
            if (shapeBound.intersects(selectionBox)){
                setShapeAsSelected(shape); // both sets field and adds to list.
            }
        }
        printSelectedShapesList();
    }

    public IShape pickUpShape(Point startPoint) {
        for (int i = shapeList.size() - 1; i >= 0; i-- ){
            IShape shape = shapeList.get(i);
            BoundingBox box = shape.getBoundingBox();
            if (box.contains(startPoint)) {
                setShapeAsSelected(shape);
                shapeList.removeShape(shape);
                shapeList.printShapeList();
                pc.clear(); // calls repaint() in canvas
                System.out.printf("\n%s selected for move.", shape.toString());
                return shape;
            }
        }
        return null;
    }

    public void dropShape(IShape shape, Point releasePoint) {

        int width = shape.getWidth();
        int height = shape.getHeight();

        shape.setStartPoint(releasePoint.getX(), releasePoint.getY());
        Point newStartPoint = shape.getStartPoint();
        shape.setEndPoint(newStartPoint.getX() + width, newStartPoint.getY()+ height);
        shapeList.addShape(shape);
        shapeList.printShapeList();
        drawShapeList();
    }

    public void deleteSelectedShapes(){

    }


//    public IShape shapeContainingPoint(Point pt) {
//        for (int i = shapeList.size() - 1; i >= 0; i--) {
//            IShape shape = shapeList.get(i);
//            BoundingBox box = shape.getBoundingBox();
//
//            if (box.contains(pt)) {
//                setSelectedShape(shape);
//                System.out.printf("\nPoint %s intersected shape %s", pt.toString(), selectedShape.printShapeType());
//                return shape;
//            }
//        }
//            System.out.println("\nCouldn't find shape in that bound.");
//            return null; // if no shape contains pt
//    }




}
