package gamelibx.game;

import gamelibx.interfaces.Rectangular;

import java.awt.geom.Rectangle2D;

public abstract class GameObject {
    private State state = State.NEUTRAL;
    private int centerX;
    private int centerY;

    public GameObject(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setPos(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void move(int dx, int dy) {
        centerX += dx;
        centerY += dy;
    }

    public enum State {
        ACTIVE,
        PASSIVE,
        NEUTRAL
    }

    public State getState() {
        return state;
    }

    public void makeActive() {
        if(this instanceof Rectangular)
            state = State.ACTIVE;
        else
            throw new IllegalStateException("Cannot make a non-rectangular object active.");
    }

    public void makePassive() {
        state = State.PASSIVE;
    }

    public void makeNeutral() {
        state = State.NEUTRAL;
    }

    public abstract boolean containsPoint(int x, int y);

    public abstract boolean containsRect(Rectangle2D rect);
}