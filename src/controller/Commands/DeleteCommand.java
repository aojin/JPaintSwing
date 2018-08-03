package controller.Commands;

import model.persistence.ApplicationState;
import model.persistence.Command;
import controller.GuiObserver;
import view.interfaces.IEventCallback;

public class DeleteCommand implements Command {

    ApplicationState applicationState;
    IEventCallback eventCallback;
    GuiObserver observer;

    public DeleteCommand(IEventCallback callback, GuiObserver observer){
        this.eventCallback = callback;
        this.observer = observer;
    }
    @Override
    public void undo() { // REVERSE: you have a selected item, and you remove from shapeList
        observer.shapeList.addShape();
    }

    @Override
    public void redo() {
        observer.shapeList.removeShape();
    }

    @Override
    public void run() {
        observer.shapeList.removeShape();
    }
}
