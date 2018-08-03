package model.interfaces;

import model.Point;
import model.shapes.BoundingBox;
import model.shapes.ShapeColor;
import model.shapes.ShapeShadingType;
import model.shapes.ShapeType;

import java.awt.*;

public interface IShape {

    void draw(Graphics2D graphics);

    boolean equals(IShape shape);

    void setStartPoint(int x, int y);

    void setEndPoint(int x, int y);

    void setShapeShadingType(ShapeShadingType shapeShadingType);

    void setPrimaryColor(ShapeColor color);

    void setSecondaryColor(ShapeColor color);

    void setSelected(boolean bool);

    BoundingBox getBoundingBox();

    void drawBoundingBox(Graphics2D g);

    int getHeight();
    int getWidth();

    Point getStartPoint();

    Point getEndPoint();

    ShapeType getShape();

    String printShapeType();

    String toString();

}
