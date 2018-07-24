package view.gui;

import controller.GuiObserver;
import controller.MouseListener;
import model.ShapeList;
import model.persistence.ApplicationState;
import view.interfaces.IPaintCanvas;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// the drawing component

public class PaintCanvas extends JComponent implements IPaintCanvas{

    private Graphics2D graphics;
    private GuiObserver observer;

    public Graphics2D getGraphics2D() {
        graphics = (Graphics2D) getGraphics(); // returns this component's graphics content. Casts to 2D;
        observer.setGraphics(graphics); // adds this component's graphics to our observer
        this.addMouseListener(new MouseListener(observer)); // creates a new MouseListener around our observer.
        return graphics;
    }

    public void setNewGuiObserver(ShapeList shapeList, ApplicationState appState) {
        observer = new GuiObserver(shapeList, appState);
    }

}


