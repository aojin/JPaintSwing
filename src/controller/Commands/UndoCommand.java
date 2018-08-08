package controller.Commands;

import model.persistence.ICommand;
import model.persistence.CommandHistory;

public class UndoCommand implements ICommand {

    public void run() {
        CommandHistory.undo();
    }

    @Override
    public void undo() {
        CommandHistory.redo();
    }

    @Override
    public void redo() {
        run();
    }
}
