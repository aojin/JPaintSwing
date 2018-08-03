package model;

// this is my "Pair" class
// package private

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Boolean equals(Point otherPoint) {
        return otherPoint.getX() == x
                && otherPoint.getY() == y;
    }

    public String toString(){
        String x = String.valueOf(this.getX());
        String y = String.valueOf(this.getY());
        return "(" + x + " , " + y + ")";
    }
}
