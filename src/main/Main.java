package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args){
        PaintCanvas pc = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(pc);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        pc.addGuiObserver(appState);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();
    }
}
