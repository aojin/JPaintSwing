package model.shapes;

import model.Point;

import java.awt.*;

public class BoundingBox {

    private int x1, x2, y1, y2;
    private int width, height;
    private int midX, midY; // for dot product collision algo

    private BoundingBox(int x1, int y1, int x2, int y2) {
        this.x1 = Math.min(x1, x2);
        this.x2 = Math.max(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.y2 = Math.max(y1, y2);
        width = x2 - x1;
        height = y2 - y1;
        midX = x1 + ((x2 - x1)/2);
        midY = y1 + ((y2 - y1)/2);
    }

    public BoundingBox(Point startPoint, Point endPoint) {
        this(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
    }

    private int getMidX() {
        return midX;
    }

    private int getMidY() {
        return midY;
    }

    public boolean intersects(BoundingBox shapeBox) {

        return Math.abs(this.getMidX() - shapeBox.getMidX()) < (Math.abs(this.width + shapeBox.width) / 2)

                && (Math.abs(this.getMidY() - shapeBox.getMidY()) < (Math.abs(this.height + shapeBox.height) / 2));

    }


    public boolean contains(Point pt) {
        return (pt.getX() >= x1 && pt.getX() <= x2 && pt.getY() >= y1 && pt.getY() <= y2);
    }

    void drawBoundingBox(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.drawRect(x1, y1, x2-x1, y2-y1);
    }

}