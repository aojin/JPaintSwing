package model.interfaces;

import model.persistence.ApplicationState;
import model.shapes.ShapeColor;
import model.shapes.ShapeShadingType;
import model.shapes.ShapeType;
import model.StartAndEndPointMode;

public interface IApplicationState {
    void setActiveShape();

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType();

    void setActiveStartAndEndPointMode();

    ShapeType getActiveShapeType();

    ShapeColor getActivePrimaryColor();

    ShapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    StartAndEndPointMode getActiveStartAndEndPointMode();

    //for undo redo

    void setActivePrimaryColorByParameter(ShapeColor previousPrimaryColor);
    void setActiveSecondaryColorByParameter(ShapeColor previousSecondaryColor);
    void setActiveShapeTypeByParameter(ShapeType previousShapeType);
    void setActiveShapeShadingTypeByParameter(ShapeShadingType previousShapeShadingType);
    void setActiveStartAndEndPointModeByParameter(StartAndEndPointMode startAndEndPointMode);

}
