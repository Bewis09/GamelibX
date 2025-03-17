package gamelibx;

import gamelibx.game.GameObject;
import gamelibx.interfaces.Rectangular;

import java.awt.geom.Rectangle2D;

public class Rectangle extends GameObject implements Rectangular {
    int width;
    int height;

    public Rectangle(int x, int y, int width, int height) {
        super(x + width / 2, y + height / 2);
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean containsPoint(int x, int y) {
        return x >= getCenterX() - width / 2 && x <= getCenterX() + width / 2 &&
                y >= getCenterY() - height / 2 && y <= getCenterY() + height / 2;
    }

    @Override
    public boolean containsRect(Rectangle2D rect) {
        return rect.contains(getCenterX() - width / 2f, getCenterY() - height / 2f, width, height);
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(getCenterX() - width / 2f, getCenterY() - height / 2f, width, height);
    }
}
