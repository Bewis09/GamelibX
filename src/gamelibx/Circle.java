package gamelibx;

import gamelibx.drawing.DrawStyle;
import gamelibx.game.ShapedGameObject;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.geom.Arc2D;

public class Circle extends ShapedGameObject {
    /**
     * A game object that is represented by a Circle.
     *
     * @param centerX       The center x-coordinate of the object.
     * @param centerY       The center y-coordinate of the object.
     * @param radius        The radius of the circle.
     * @param drawStyle     The style to draw the object with.
     */
    public Circle(float centerX, float centerY, int radius, @Nullable DrawStyle drawStyle) {
        super(centerX, centerY, new Arc2D.Float(-radius, -radius, radius * 2, radius * 2, 0, 360, Arc2D.CHORD), drawStyle);
    }

    /**
     * A game object that is represented by a Circle.
     *
     * @param centerX       The center x-coordinate of the object.
     * @param centerY       The center y-coordinate of the object.
     * @param radius        The radius of the circle.
     */
    public Circle(float centerX, float centerY, int radius) {
        this(centerX, centerY, radius, null);
    }
}
