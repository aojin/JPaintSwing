package model.persistence;

public interface ICommand {
    // adding additional functionality the objects we're stacking in history. Each type of command must know how to undo itself and needs to provide default run method which just uses the eventcallback run functionality given by lambda in the jcontroller setup function.

    //right now this is including dialog selection panes....which isn't right.
    void undo();
    void redo();
    void run();
}
