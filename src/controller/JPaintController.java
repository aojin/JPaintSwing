package controller;

import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule; // the view
    private final IApplicationState applicationState; // the model


    // need to connect my Command Pattern to take eventCallback runs from GUI and translate them into model implementations.


    public JPaintController(IUiModule uiModule, IApplicationState applicationState) { // CONTROLLER INITIALIZER HAS MODEL AND VIEW, VIEW IS COMMUNICATED THROUGH OBSERVER
        this.uiModule = uiModule;
        this.applicationState = applicationState;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    public IApplicationState getApplicationState() {
        return applicationState;
    }


    public IUiModule getUiModule() { return uiModule; }


    private void setupEvents() { // through the controller, the View generates events and the Model gets data from view...

        uiModule.addEvent(EventName.CHOOSE_SHAPE, applicationState :: setActiveShape);
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, applicationState::setActivePrimaryColor);
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, applicationState::setActiveSecondaryColor);
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, applicationState::setActiveShadingType);
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, applicationState::setActiveStartAndEndPointMode);
//        uiModule.addEvent(EventName.DELETE, GuiObserver :: deleteSelectedShapes);
    }




}
