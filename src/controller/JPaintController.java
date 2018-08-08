package controller;

import controller.Commands.CopyCommand;
import model.interfaces.IApplicationState;
// import model.persistence.CommandFactory;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule; // the view
    private final IApplicationState applicationState; // the model
    private final GuiObserver observer;

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

        uiModule.addEvent(EventName.DELETE, observer :: deleteSelectedShapes);
//        uiModule.addEvent(EventName.UNDO, () -> new UndoCommand().run());
//        uiModule.addEvent(EventName.REDO, () -> new RedoCommand().run());
        uiModule.addEvent(EventName.COPY, () -> {
            new CopyCommand(observer).run();
            observer.setClipBoard();
        });
//        uiModule.addEvent(EventName.PASTE, () -> new PasteCommand(mouse.getClipboard(),mouse.getCanvas(),mouse.getShapeList()).run());

    }




}
