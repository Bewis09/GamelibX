package gamelibx.interfaces;

import gamelibx.Game;

import java.awt.geom.Rectangle2D;

public interface Collidable {
    boolean containsPoint(int x, int y);

    boolean containsRect(Rectangle2D rect);

    default void onCollide(Game game) {}

    boolean shouldCollide();

    boolean isHidden();
}