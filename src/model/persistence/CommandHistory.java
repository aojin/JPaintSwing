package model.persistence;

import java.util.Stack;

public class CommandHistory {
	private static final Stack<Command> undoStack = new Stack<>();
	private static final Stack<Command> redoStack = new Stack<>();

	public static void add(Command command) {
		undoStack.push(command);
		redoStack.clear();
	}

	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			Command e = undoStack.pop();
			redoStack.push(e);
			e.undo(); // calls the objects' Undo or redo
			System.out.printf("Called undo on last %s",e.getClass().getName());
		}
		return result;
	}

	public static boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			Command e = redoStack.pop();
			undoStack.push(e);
			e.redo();
			System.out.printf("Called redo on last %s",e.getClass().getName());
		}
		return result;
	}

	// For testing
	Command topUndoCommand() {
		if (undoStack.empty())
			return null;
		else
			return undoStack.peek();
	}
	// For testing
	Command topRedoCommand() {
		if (redoStack.empty())
			return null;
		else
			return redoStack.peek();
	}

}
