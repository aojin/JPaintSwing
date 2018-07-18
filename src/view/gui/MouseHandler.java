package view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MouseHandler extends MouseAdapter {

    private JPanel mousePanel;
    private JLabel statusBar;

    MouseHandler(JPanel window){
        this.mousePanel = window;
        this.statusBar = createStatusBar(window);
    }

    private JLabel createStatusBar(JPanel window) {
        JLabel statusBar = new JLabel("Status: "); // <--- For feedback
        window.add(statusBar, BorderLayout.SOUTH);
        return statusBar;
    }

    // These are the MouseListener methods.
    @Override
    public void mouseClicked(MouseEvent event){
        Point clickedPoint = new Point(event.getX(), event.getY());
        statusBar.setText(String.format("Clicked at (%d, %d).", clickedPoint.getX(), clickedPoint.getY()));
        // Add your persistence structure here.. History.add(clickedPoint)
    }

    @Override
    public void mousePressed(MouseEvent event){
        Point heldPoint = new Point(event.getX(), event.getY());
        statusBar.setText(String.format("You pressed down the mouse and held at (%d, %d).", heldPoint.getX(), heldPoint.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent event){
        Point releasedPoint = new Point(event.getX(), event.getY());
        statusBar.setText(String.format("You released the held press at (%d, %d).", releasedPoint.getX(), releasedPoint.getY()));
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        statusBar.setText("Mouse has entered our PaintCanvas Window.");
        mousePanel.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        statusBar.setText("Mouse has exited our PaintCanvas Window.");
        mousePanel.setBackground(Color.WHITE);
    }

    // Now we include our MouseMotionListener methods to finish up our handler.

    @Override
    public void mouseDragged(MouseEvent event) {
        statusBar.setText("Mouse is dragging.");
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        statusBar.setText("You moved the mouse wheel.");
    }
}
