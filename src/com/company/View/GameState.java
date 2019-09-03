package com.company.View;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class GameState {
    protected GameStateManager gsm;
    public abstract void init();
    public abstract void update();
    public abstract void paint(Graphics2D g);
    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);
}
