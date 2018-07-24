package model;

import java.awt.Color;

public enum ShapeColor {
    BLACK(Color.BLACK, "BLACK"),
    BLUE(Color.BLUE, "BLUE"),
    CYAN(Color.CYAN, "CYAN"),
    DARK_GRAY(Color.DARK_GRAY, "DARK_GREY"),
    GRAY(Color.GRAY, "GRAY"),
    GREEN(Color.GREEN, "GREEN"),
    LIGHT_GRAY(Color.LIGHT_GRAY,"LIGHT_GRAY"),
    MAGENTA(Color.MAGENTA,"MAGENTA"),
    ORANGE(Color.ORANGE,"ORANGE"),
    PINK(Color.PINK,"PINK"),
    RED(Color.RED,"RED"),
    WHITE(Color.WHITE,"WHITE"),
    YELLOW(Color.YELLOW,"YELLOW");

    private final Color awtColor;
    private final String colorName;

    ShapeColor(Color awtColor, String colorName) {
        this.awtColor = awtColor;
        this.colorName = colorName;
    }

    public Color getAwtColor(){
        return awtColor;
    }

    public String getColorName(){
        return colorName;
    }
}
