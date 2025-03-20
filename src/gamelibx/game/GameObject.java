package gamelibx.game;

import gamelibx.Game;
import gamelibx.interfaces.Collidable;
import gamelibx.interfaces.Drawable;
import gamelibx.interfaces.Rectangular;
import gamelibx.interfaces.Tickable;

public abstract class GameObject implements Drawable, Tickable {
    private State state = State.NEUTRAL;
    protected float centerX;
    protected float centerY;

    public GameObject(float centerX, float centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
        Game.getInstance().addTickable(this);
        Game.getInstance().addDrawable(this);
    }

    public int getCenterX() {
        return (int) centerX;
    }

    public int getCenterY() {
        return (int) centerY;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(float centerY) {
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

    /**
     * A game object must be rectangular to be active.
     * It is recommended to use this method only for objects that extend {@link gamelibx.Rectangle}, but not necessary.
     */
    public void makeActive() {
        if(this instanceof Rectangular) {
            if(this instanceof Collidable c)
                Game.getInstance().removeCollidable(c);
            state = State.ACTIVE;
        } else
            throw new IllegalStateException("Cannot make a non-rectangular object active.");
    }

    public void makePassive() {
        if(this instanceof Collidable c) {
            Game.getInstance().addCollidable(c);
            state = State.PASSIVE;
        } else
            throw new IllegalStateException("Cannot make a non-collidable object passive");
    }

    public void makeNeutral() {
        if(this instanceof Collidable c)
            Game.getInstance().removeCollidable(c);
        state = State.NEUTRAL;
    }
}