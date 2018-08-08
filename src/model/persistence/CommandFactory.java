//package model.persistence;
//
//import controller.Commands.*;
//import controller.GuiObserver;
//import view.EventName;
//import view.interfaces.IEventCallback;
//
//public class CommandFactory {
//
//    private static ICommand command;
//    GuiObserver observer;
//
//    public CommandFactory(GuiObserver observer){
//        this.observer = observer;
//    }
//
//    public static ICommand createCommand(EventName eventName, IEventCallback callback, GuiObserver observer) {
//
//        switch (eventName) {
//            case COPY:
//                command = new CopyCommand(callback, observer);
//            case PASTE:
//                command = new PasteCommand(callback, observer);
//            case UNDO:
//                command = new UndoCommand(callback, observer);
//            case REDO:
//                command = new RedoCommand(callback, observer);
//            case DELETE:
//                command = new DeleteCommand(callback, observer);
//            default:
//                command = new DrawCommand();
//        }
//
//        return command;
//    }
//}
