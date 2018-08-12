package controller;

import controller.Commands.CopyCommand;
import controller.Commands.DeleteCommand;
import controller.Commands.PasteCommand;
import model.interfaces.IApplicationState;
import model.persistence.ICommand;
import view.EventName;
import view.interfaces.IUiModule;
import model.persistence.CommandHistory;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule; // the view
    private final IApplicationState applicationState; // the model
    private final GuiObserver observer;
    private final CommandHistory commandHistory = new CommandHistory();

    // need to connect my ICommand Pattern to take eventCallback runs from GUI and translate them into model implementations.


    public JPaintController(IUiModule uiModule, GuiObserver observer, IApplicationState applicationState) { // CONTROLLER INITIALIZER HAS MODEL AND VIEW, VIEW IS COMMUNICATED THROUGH OBSERVER
        this.uiModule = uiModule;
        this.observer = observer;
        this.applicationState = applicationState;
    }

    @Override
    public void setup() {
        setupEvents();
    }


    private void setupEvents() { // through the controller, the View generates events and the Model gets data from view...
        uiModule.addEvent(EventName.CHOOSE_SHAPE, applicationState :: setActiveShape);
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, applicationState::setActivePrimaryColor);
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, applicationState::setActiveSecondaryColor);
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, applicationState::setActiveShadingType);
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, applicationState::setActiveStartAndEndPointMode);
        uiModule.addEvent(EventName.CLEAR, observer :: clearCanvas);

        uiModule.addEvent(EventName.DELETE, ()-> {
            ICommand command = new DeleteCommand(observer);
            CommandHistory.add(command);
            command.run();
        });
        uiModule.addEvent(EventName.UNDO, CommandHistory::undo);
        uiModule.addEvent(EventName.REDO, CommandHistory::redo);
        uiModule.addEvent(EventName.COPY, () -> {
            ICommand command = new CopyCommand(observer);
            CommandHistory.add(command);
            command.run();
        });
        uiModule.addEvent(EventName.PASTE, () -> {
            if (observer.getClipboard() != null) {
                ICommand command = new PasteCommand(observer);
                CommandHistory.add(command);
                command.run();
            }
            else {
                System.out.println("There's nothing to paste on the current clipboard...");
            }
        });


    }




}
