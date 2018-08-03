package controller;

import model.Point;
import java.awt.event.*;

public class MouseListener extends MouseAdapter {
    private GuiObserver observer;
    private Point startPoint = new Point(0,0);
    private Point endPoint = new Point(0,0);

    public MouseListener(GuiObserver observer) {
        this.observer = observer;
    }

    public void mousePressed(MouseEvent e) {
        startPoint.setX(e.getX());
        startPoint.setY(e.getY());
        System.out.printf("\nMouse held at point (%d, %d)", startPoint.getX(), startPoint.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e){
        endPoint.setX(e.getX());
        endPoint.setY(e.getY());
        System.out.printf("\nMouse press released at (%d, %d)",endPoint.getX(),endPoint.getY());
        observer.buildShape(startPoint, endPoint);
    }
}

