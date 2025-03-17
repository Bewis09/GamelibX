package gamelibx.game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PolygonalGameObject extends GameObject {
    private final Polygon relativePolygon;

    /**
     * A game object that is represented by a polygon.
     *
     * @param centerX The center x-coordinate of the object.
     * @param centerY The center y-coordinate of the object.
     * @param relativePolygon The polygon that represents the object where the origin is the center of the object.
     */
    public PolygonalGameObject(int centerX, int centerY, Polygon relativePolygon) {
        super(centerX, centerY);
        this.relativePolygon = relativePolygon;
    }

    @Override
    public boolean containsPoint(int x, int y) {
        return relativePolygon.contains(x - getCenterX(), y - getCenterY());
    }

    @Override
    public boolean containsRect(Rectangle2D rect) {
        int x = (int) rect.getX() - getCenterX();
        int y = (int) rect.getY() - getCenterY();

        return relativePolygon.intersects(x, y, rect.getWidth(), rect.getHeight());
    }
}
