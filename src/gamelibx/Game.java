package gamelibx;

import gamelibx.interfaces.Drawable;
import gamelibx.interfaces.Tickable;
import gamelibx.swing.GameXWindow;

import java.awt.*;
import java.util.ArrayList;

public class Game implements Tickable {
    private static Game instance;

    private final GameXWindow window;
    private final ArrayList<Tickable> tickables = new ArrayList<>();
    private final ArrayList<Drawable> drawables = new ArrayList<>();

    /**
     * Creates a new game with the given title and dimensions.
     * There should only ever be one game at once.
     *
     * @throws IllegalStateException If there is already a game.
     *
     * @param title  The title of the game.
     * @param width  The width of the game window.
     * @param height The height of the game window.
     */
    public Game(String title, int width, int height) {
        if(getInstance() != null) {
            throw new IllegalStateException("There is already a game.");
        }

        instance = this;

        window = new GameXWindow(title, width, height);
    }

    public static Game getInstance() {
        return instance;
    }

    public int getWidth() {
        return window.getWidth();
    }

    public int getHeight() {
        return window.getHeight();
    }

    public void setTitle(String title) {
        window.setTitle(title);
    }

    public final void paint(Graphics g) {
        for (Drawable drawable : drawables) {
            drawable.draw();
        }

        paintAdditions(g);
    }

    public final void onTick() {
        this.tick();
        for (Tickable tickable : tickables) {
            tickable.tick();
        }
        window.repaint();
    }

    public final void addTickable(Tickable tickable) {
        tickables.add(tickable);
    }

    public final void removeTickable(Tickable tickable) {
        tickables.remove(tickable);
    }

    /**
     * This method can be overridden to paint additional elements to the game.
     * It is called after the main paint method.
     *
     * @param g The Graphics object to paint with.
     */
    public void paintAdditions(Graphics g) {

    }

    @Override
    public void tick() {

    }
}
