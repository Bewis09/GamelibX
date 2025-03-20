package gamelibx;

import gamelibx.interfaces.Collidable;
import gamelibx.interfaces.Drawable;
import gamelibx.interfaces.Tickable;
import gamelibx.swing.GameXWindow;
import gamelibx.ticker.TickManager;
import gamelibx.util.KotlinLikeHelpers;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game implements Tickable, KeyListener, MouseListener {
    private static Game instance;

    private final GameXWindow window;
    private final ArrayList<Tickable> tickables = new ArrayList<>();
    private final ArrayList<Drawable> drawables = new ArrayList<>();
    private List<Collidable> collidables = Collections.unmodifiableList(new ArrayList<>());

    public float defaultGravity = 0.1f;

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

        TickManager.run();
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

    public float getDefaultGravity() {
        return defaultGravity;
    }

    public void setDefaultGravity(float defaultGravity) {
        this.defaultGravity = defaultGravity;
    }

    public final void paint(Graphics2D g) {
        for (Drawable drawable : drawables.toArray(new Drawable[0])) {
            drawable.draw(g);
        }

        paintAdditions(g);
    }

    public final void onRender() {
        window.repaint();
    }

    public final void onTick() {
        this.tick();
        for (Tickable tickable : tickables) {
            tickable.tick();
        }
    }

    public final void addTickable(Tickable tickable) {
        tickables.add(tickable);
    }

    public final void removeTickable(Tickable tickable) {
        tickables.remove(tickable);
    }

    public final void addDrawable(Drawable drawable) {
        drawables.add(drawable);
    }

    public final void removeDrawable(Drawable drawable) {
        drawables.remove(drawable);
    }

    public final void addCollidable(Collidable collidable) {
        collidables = KotlinLikeHelpers.apply(new ArrayList<>(collidables), (i) -> i.add(collidable)).stream().toList();
    }

    public final void removeCollidable(Collidable collidable) {
        collidables = KotlinLikeHelpers.apply(new ArrayList<>(collidables), (i) -> i.remove(collidable)).stream().toList();
    }

    public final List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * This method can be overridden to paint additional elements to the game.
     * It is called after the main paint method.
     *
     * @param g The Graphics2D object to paint with.
     */
    public void paintAdditions(Graphics2D g) {

    }

    @Override
    public void tick() {

    }

    /**
     * Starts the game ticker. The Game always runs at 60 tps
     */
    public void startTicker() {
        TickManager.setTickRate();
    }

    public void stopTicker() {
        TickManager.interrupt();
    }

    @Override
    public final void keyTyped(KeyEvent e) {
        keyTyped(e.getKeyChar(), e.getKeyCode());
    }

    public void keyTyped(char c, int code) {

    }

    @Override
    public final void keyPressed(KeyEvent e) {
        keyPressed(e.getKeyChar(), e.getKeyCode());
    }

    public void keyPressed(char c, int code) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyReleased(e.getKeyChar(), e.getKeyCode());
    }

    public void keyReleased(char c, int code) {

    }

    @Override
    public final void mouseClicked(MouseEvent e) {
        mouseClicked(e.getX(), e.getY(), e.getButton());
    }

    public void mouseClicked(int x, int y, int button) {

    }

    @Override
    public final void mousePressed(MouseEvent e) {
        mousePressed(e.getX(), e.getY(), e.getButton());
    }

    public void mousePressed(int x, int y, int button) {

    }

    @Override
    public final void mouseReleased(MouseEvent e) {
        mouseReleased(e.getX(), e.getY(), e.getButton());
    }

    public void mouseReleased(int x, int y, int button) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
