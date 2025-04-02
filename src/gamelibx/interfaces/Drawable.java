package gamelibx.interfaces;

import java.awt.*;

public interface Drawable {
    void draw(Graphics2D graphics2D);

    default boolean isVisible() {
        return true;
    }
}
