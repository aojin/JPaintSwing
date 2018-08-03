package model.shapes;

import model.Point;
import model.interfaces.IShape;

import java.awt.*;

public class Rectangle implements IShape {

    private ShapeType shapeType = ShapeType.RECTANGLE;
    private Point startPoint = new Point(0,0);
    private Point endPoint = new Point(0,0);
    private ShapeShadingType shapeShadingType;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private int width, height;

    private boolean selected;
    private BoundingBox box;


    Rectangle(){
    }


    @Override
    public void draw(Graphics2D graphics) {
        int x1 = Math.min(startPoint.getX(), endPoint.getX());
        int x2 = Math.max(startPoint.getX(), endPoint.getX());
        int y1 = Math.min(startPoint.getY(), endPoint.getY());
        int y2 = Math.max(startPoint.getY(), endPoint.getY());

        box = new BoundingBox(startPoint, endPoint);

        width = x2 - x1;
        height = y2 - y1;

        if (shapeShadingType == ShapeShadingType.FILLED_IN) {
            graphics.setColor(primaryColor.getAwtColor());
            graphics.fillRect(x1, y1, width, height);
        } else if (shapeShadingType == ShapeShadingType.OUTLINE) {
            graphics.setColor(primaryColor.getAwtColor());
            graphics.drawRect(x1, y1, width, height);
        } else {
            graphics.setColor(primaryColor.getAwtColor());
            graphics.fillRect(x1, y1, width, height);
            graphics.setColor(secondaryColor.getAwtColor());
            graphics.drawRect(x1, y1, width, height);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean equals(IShape otherShape) {
        return otherShape.getStartPoint().equals(startPoint) && otherShape.getEndPoint().equals(endPoint) && otherShape.getShape().equals(shapeType);
    }


    public ShapeType getShape() { return shapeType; }

    private ShapeColor getPrimaryColor() {
        return primaryColor;
    }

    private ShapeColor getSecondaryColor() {
        return secondaryColor;
    }

    private ShapeShadingType getShapeShadingType() {
        return shapeShadingType;
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
    public void setShapeShadingType(ShapeShadingType shapeShadingType) {
        this.shapeShadingType = shapeShadingType;
    }

    @Override
    public void setPrimaryColor(ShapeColor primaryColor) {
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

    public void drawBoundingBox(Graphics2D g) {
        box.drawBoundingBox(g);
    }

    public void setStartPoint(int x, int y) {
        startPoint.setX(x);
        startPoint.setY(y);
    }

    public void setEndPoint(int x, int y){
        endPoint.setX(x);
        endPoint.setY(y);
    }

    public String printShapeType() {
        return "Rectangle";
    }

    @Override
    public String toString() {
       return "Shape: " + this.getShape() + "  Primary Color: " + this.getPrimaryColor() + "  Secondary Color: " + this.getSecondaryColor() + "  Shading Type : " + this.getShapeShadingType() + "  Start: " + this.startPoint.toString() + "  End: " + this.endPoint.toString();
    }
}
