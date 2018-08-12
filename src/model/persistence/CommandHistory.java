package model.persistence;

import java.util.Stack;

public class CommandHistory {
	private static final Stack<ICommand> undoStack = new Stack<>();
	private static final Stack<ICommand> redoStack = new Stack<>();

	public static void add(ICommand ICommand) {
		undoStack.push(ICommand);
		redoStack.clear();
	}

	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			ICommand e = undoStack.pop();
			redoStack.push(e);
			e.undo(); // calls the objects' Undo or redo
			System.out.printf("Command History: Called undo on last %s",e.getClass().getName());
		}
		return result;
	}

	public static boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			ICommand e = redoStack.pop();
			undoStack.push(e);
			e.redo();
			System.out.printf("Command History: Called redo on last %s",e.getClass().getName());
		}
		return result;
	}

	// For testing
	ICommand topUndoICommand() {
		if (undoStack.empty())
			return null;
		else
			return undoStack.peek();
	}
	// For testing
	ICommand topRedoICommand() {
		if (redoStack.empty())
			return null;
		else
			return redoStack.peek();
	}

}
