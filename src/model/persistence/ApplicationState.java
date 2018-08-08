package model.persistence;


import model.shapes.ShapeColor;
import model.shapes.ShapeShadingType;
import model.shapes.ShapeType;
import model.StartAndEndPointMode;
import model.dialogs.DialogProvider;
import model.interfaces.IApplicationState;
import model.interfaces.IDialogProvider;
import view.interfaces.IUiModule;

public class ApplicationState implements IApplicationState {
    private final IUiModule uiModule;
    private final IDialogProvider dialogProvider;

    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndEndPointMode activeStartAndEndPointMode;


    public ApplicationState(IUiModule uiModule) {
        this.uiModule = uiModule;
        this.dialogProvider = new DialogProvider(this);

        setDefaults();
    }

    @Override
    public void setActiveShape() {
        activeShapeType = uiModule.getDialogResponse(dialogProvider.getChooseShapeDialog());
        System.out.println("\nActive Shape Type set to " + activeShapeType);
    }

    @Override
    public void setActiveShapeTypeByParameter(ShapeType previousShapeType) {
        activeShapeType = previousShapeType;
    }

    @Override
    public void setActivePrimaryColor() {
        activePrimaryColor = uiModule.getDialogResponse(dialogProvider.getChoosePrimaryColorDialog());
        System.out.println("\nActive Primary Color set to " + activePrimaryColor);
    }

    public void setActivePrimaryColorByParameter(ShapeColor previousPrimaryColor){
        activePrimaryColor = previousPrimaryColor;
    }

    @Override
    public void setActiveSecondaryColor() {
        activeSecondaryColor = uiModule.getDialogResponse(dialogProvider.getChooseSecondaryColorDialog());
        System.out.println("\nActive Secondary Color set to " + activeSecondaryColor);
    }

    @Override
    public void setActiveSecondaryColorByParameter(ShapeColor previousSecondaryColor) {
        activeSecondaryColor = previousSecondaryColor;
    }

    @Override
    public void setActiveShadingType() {
        activeShapeShadingType = uiModule.getDialogResponse(dialogProvider.getChooseShadingTypeDialog());
        System.out.println("\nActive Shape Shading Type set to " + activeShapeShadingType);
    }

    public void setActiveShapeShadingTypeByParameter(ShapeShadingType previousShapeShadingType){
        activeShapeShadingType = previousShapeShadingType;
    }

    @Override
    public void setActiveStartAndEndPointMode() {
        activeStartAndEndPointMode = uiModule.getDialogResponse(dialogProvider.getChooseStartAndEndPointModeDialog());
        System.out.println("\nActive Start And End Point Mode set to " + activeStartAndEndPointMode);
    }

    public void setActiveStartAndEndPointModeByParameter(StartAndEndPointMode startAndEndPointMode) {
        activeStartAndEndPointMode = startAndEndPointMode;
    }


    @Override
    public ShapeType getActiveShapeType() {
        return activeShapeType;
    }

    @Override
    public ShapeColor getActivePrimaryColor() {
        return activePrimaryColor;
    }

    @Override
    public ShapeColor getActiveSecondaryColor() {
        return activeSecondaryColor;
    }

    @Override
    public ShapeShadingType getActiveShapeShadingType() {
        return activeShapeShadingType;
    }


    @Override
    public StartAndEndPointMode getActiveStartAndEndPointMode() {
        return activeStartAndEndPointMode;
    }

    private void setDefaults() {
        activeShapeType = ShapeType.ELLIPSE;
        activePrimaryColor = ShapeColor.BLUE;
        activeSecondaryColor = ShapeColor.GREEN;
        activeShapeShadingType = ShapeShadingType.FILLED_IN;
        activeStartAndEndPointMode = StartAndEndPointMode.DRAW;
    }
}
