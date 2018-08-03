package controller;

import view.gui.PaintCanvas;

import java.awt.*;

public interface IObserver {
    void update(PaintCanvas pc, Object arg);
}
