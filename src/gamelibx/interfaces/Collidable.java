package gamelibx.interfaces;

import java.awt.geom.Rectangle2D;

public interface Collidable {
    boolean containsPoint(int x, int y);

    boolean containsRect(Rectangle2D rect);
}