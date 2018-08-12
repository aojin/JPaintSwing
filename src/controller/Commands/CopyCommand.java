package controller.Commands;

import controller.GuiObserver;
import model.Clipboard;
import model.persistence.ICommand;

import java.util.ArrayList;

public class CopyCommand implements ICommand {
    Clipboard clipboard;
    GuiObserver observer;



    public CopyCommand(GuiObserver observer) {
        this.observer = observer;
    }

    public void run(){
        observer.setClipboard(); // creates new Clipboard object containing currently selected shapes from the observer
        this.clipboard = observer.getClipboard();
        System.out.println("\nAll selected items copied to clipboard");
        System.out.printf("Clipboard: %s", clipboard.toString());
    }

    public void undo(){
        observer.clearClipboard();
        observer.allSelectedShapes.clear();
        System.out.println("\nCleared Clipboard...\n");
    }

    public void redo(){
    }

}
