package view.interfaces;

// The command interface to which we can append new event functionality i.e. undo

// we must create the command classes to individual actions per event callback.

public interface IEventCallback {
	void run();
}
