package model.dialogs;

import model.shapes.ShapeType;
import model.interfaces.IApplicationState;
import view.interfaces.IDialogChoice;

public class ChooseShapeDialog implements IDialogChoice<ShapeType> {
    private final IApplicationState applicationState;

    ChooseShapeDialog(IApplicationState applicationState) {

        this.applicationState = applicationState;
    }

    @Override
    public String getDialogTitle() {
        return "ShapeSelector";
    }

    @Override
    public String getDialogText() {
        return "Select a shape from the menu below:";
    }

    @Override
    public ShapeType[] getDialogOptions() {
        return ShapeType.values();
    }

    @Override
    public ShapeType getCurrentSelection() {
        return applicationState.getActiveShapeType();
    }
}
