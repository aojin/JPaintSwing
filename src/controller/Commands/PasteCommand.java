package controller.Commands;

import controller.GuiObserver;
import model.Clipboard;
import model.persistence.ICommand;

public class PasteCommand implements ICommand {

    Clipboard clipboard;
    GuiObserver observer;

    public PasteCommand(GuiObserver observer) {

    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    public void run() {

    }
}
