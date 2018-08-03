package model.shapes;

import model.Point;
import model.interfaces.IShape;

import java.awt.*;

public class Ellipse implements IShape {

    private ShapeType shapeType = ShapeType.ELLIPSE;
    private Point startPoint = new Point(0,0);
    private Point endPoint = new Point(0,0);

    private ShapeShadingType shapeShadingType;
    private ShapeColor primaryColor ;
    private ShapeColor secondaryColor;
    private int width, height;
    private boolean selected;
    private BoundingBox box;

    Ellipse(){
    }

    @Override
    public void draw(Graphics2D graphics){

        int x1 = Math.min(startPoint.getX(), endPoint.getX());
        int x2 = Math.max(startPoint.getX(), endPoint.getX());
        int y1 = Math.min(startPoint.getY(), endPoint.getY());
        int y2 = Math.max(startPoint.getY(), endPoint.getY());

        box = new BoundingBox(startPoint, endPoint);
        width = x2-x1;
        height = y2-y1;

        if (shapeShadingType == ShapeShadingType.FILLED_IN) {
            graphics.setColor(primaryColor.getAwtColor());
            graphics.fillOval(x1, y1,
                    width, height);
        } else if (shapeShadingType == ShapeShadingType.OUTLINE) {
            graphics.setColor(primaryColor.getAwtColor());
            graphics.drawOval(x1, y1,
                    width, height);
        } else {
            graphics.setColor(primaryColor.getAwtColor());
            graphics.fillOval(x1, y1,
                    width, height);
            graphics.setColor(secondaryColor.getAwtColor());
            graphics.drawOval(x1, y1,
                    width, height);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(IShape otherShape) {
        if (otherShape.getStartPoint().equals(startPoint))
            if (otherShape.getEndPoint().equals(endPoint)) {
                return otherShape.equals(this);
            }
        return false;
    }

    @Override
    public void setStartPoint(int x, int y) {
        startPoint.setX(x);
        startPoint.setY(y);
    }

    @Override
    public void setEndPoint(int x, int y) {
        endPoint.setX(x);
        endPoint.setY(y);
    }

    @Override
    public Point getStartPoint() {
        return startPoint;
    }

    @Override
    public Point getEndPoint() {
        return endPoint;
    }

    @Override
    public ShapeType getShape() { return shapeType; }

    private ShapeShadingType getShapeShadingType() {
        return shapeShadingType;
    }

    private ShapeColor getPrimaryColor() {
        return primaryColor;
    }

    private ShapeColor getSecondaryColor() {
        return secondaryColor;
    }

    @Override
    public void setShapeShadingType(ShapeShadingType shapeShadingType) {
        this.shapeShadingType = shapeShadingType;
    }

    @Override
    public void setPrimaryColor(ShapeColor primaryColor){
        this.primaryColor = primaryColor;
    }

    @Override
    public void setSecondaryColor(ShapeColor secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    @Override
    public void setSelected(boolean bool) {
        selected = bool;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return box;
    }

    public String printShapeType() {
        return "Ellipse";
    }

    @Override
    public String toString() {
        return "Shape: " + this.getShape() + "  Primary Color: " + this.getPrimaryColor() + "  Secondary Color: " + this.getSecondaryColor() + "  Shading Type : " + this.getShapeShadingType() + "  Start: " + this.startPoint.toString() + "  End: " + this.endPoint.toString();
    }

    public void drawBoundingBox(Graphics2D g) {
        box.drawBoundingBox(g);
    }

}
