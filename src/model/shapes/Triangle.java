package model.shapes;

import model.Point;

import model.shapes.ShapeColor;
import model.shapes.ShapeShadingType;
import model.shapes.ShapeType;
import model.interfaces.IShape;

import java.awt.*;

public class Triangle implements IShape{

    private ShapeType shapeType = ShapeType.TRIANGLE;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private ShapeShadingType shapeShadingType;
    private Point startPoint = new Point(0,0);
    private Point endPoint = new Point(0,0);
    private Point[] v; // list of vertex points
    private int n = 3; // number of vertices
    private BoundingBox box;
    private boolean selected;
    private int width, height;


    Triangle(){

    }

    private int height()
    // post: returns the height of the triangle
    {
        return bottom()-top();
    }

    private int width()
    // post: returns the width of the triangle
    {
        return right()-left();
    }

    private int left()
    // post: returns the left-most coordinate of the bounding
    //       box containing the triangle
    {
        int i;
        int result = v[0].getX();
        for (i = 1; i < n; i++)
        {
            result = Math.min(result,v[i].getX());
        }
        return result;
    }

    private int right()
    // post: returns the right-most coordinate of the bounding
    //       box containing the triangle
    {
        int i;
        int result = v[0].getX();
        for (i = 0; i < 3; i++)
        {
            result = Math.max(result,v[i].getX());
        }
        return result;
    }

    private int bottom()
    // post: returns the bottom-most coordinate of the bounding
    //       box containing the triangle
    {
        int i;
        int result = v[0].getY();
        for (i = 0; i < n; i++)
        {
            result = Math.max(result,v[i].getY());
        }
        return result;
    }

    private int top()
    // post: returns the top-most coordinate of the bounding
    //       box containing the triangle
    {
        int i;
        int result = v[0].getY();
        for (i = 0; i < n; i++)
        {
            result = Math.min(result,v[i].getY());
        }
        return result;
    }

    private Point center()
    // post: returns the center of the bounding box of the triangle
    {
        return new Point((left()+right())/2,(top()+bottom())/2);
    }


    @Override
    public void draw(Graphics2D graphics) {
        int qx = 0, rx= 0, qy= 0, ry= 0;
        int px = Math.min(startPoint.getX(), endPoint.getX());
        int py = 0;
        if(px == startPoint.getX()){
            py = startPoint.getY();
            qx = endPoint.getX();
            qy = startPoint.getY();
            rx = Math.max(startPoint.getX(), startPoint.getX());
            ry = endPoint.getY();
        }
        else if(px == endPoint.getX()){
            py = startPoint.getY();
            qx = startPoint.getX();
            qy = endPoint.getY();
            rx = endPoint.getX();
            ry = endPoint.getY();
        }

        int [] xArray = {qx,px,rx};
        int [] yArray = {qy,py,ry};

        v = new Point[3];

        v[0] = new Point(px, py);
        v[1] = new Point(qx, qy);
        v[2] = new Point(rx, ry);

        width = width();
        height = height();

        Point sPoint = new Point(Math.min(startPoint.getX(), endPoint.getX()), Math.min(startPoint.getY(), endPoint.getY()));
        Point ePoint = new Point(Math.max(startPoint.getX(), endPoint.getX()), Math.max(startPoint.getY(), endPoint.getY()));

        box = new BoundingBox(sPoint, ePoint);

        graphics.setColor(primaryColor.getAwtColor());

        if(shapeShadingType == ShapeShadingType.FILLED_IN) {
            graphics.setColor(primaryColor.getAwtColor());
            graphics.fillPolygon(xArray, yArray, 3);
        } else if (shapeShadingType == ShapeShadingType.OUTLINE){
            graphics.setColor(primaryColor.getAwtColor());
            graphics.drawPolygon(xArray,yArray,3);
        } else {
            graphics.setColor(primaryColor.getAwtColor());
            graphics.fillPolygon(xArray,yArray,3);
            graphics.setColor(secondaryColor.getAwtColor());
            graphics.drawPolygon(xArray,yArray,3);
        }

        System.out.println("\nxArray: ");
        for (int aXArray : xArray) {
            System.out.print(aXArray + " ");
        }
        System.out.println("\nyArray: ");
        for (int aYArray : yArray) {
            System.out.print(aYArray + " ");
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public boolean equals(IShape shape) {
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

    private Point setMidpoint()
    {
        int x = right() - left();
        int y = center().getY() ;

        Point midPoint = new Point(x, y);
        v[2] = midPoint;
        return midPoint;
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

    @Override
    public Point getStartPoint() {
        return startPoint;
    }

    @Override
    public Point getEndPoint() {
        return endPoint;
    }

    @Override
    public ShapeType getShape() {
        return shapeType;
    }

    public ShapeColor getPrimaryColor() {
        return primaryColor;
    }

    public ShapeColor getSecondaryColor() {
        return secondaryColor;
    }

    public ShapeShadingType getShapeShadingType() {
        return shapeShadingType;
    }

    @Override
    public String printShapeType() {
        return "Triangle";
    }

    @Override
    public String toString() {
            return "Shape: " + this.getShape() + "  Primary Color: " + this.getPrimaryColor() + "  Secondary Color: " + this.getSecondaryColor() + "  Shading Type : " + this.getShapeShadingType() + "  Start: " + this.startPoint.toString() + "  End: " + this.endPoint.toString();
        }
    }


