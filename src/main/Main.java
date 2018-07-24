package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.ShapeList;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ShapeList shapeList = new ShapeList(new ArrayList<>());
        PaintCanvas pc = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(pc);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();

        pc.setNewGuiObserver(shapeList, appState);
        pc.getGraphics2D();
    }
}
