package gamelibx.game;

import gamelibx.drawing.DrawStyle;
import gamelibx.interfaces.Collidable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class ShapedGameObject extends GameObject implements Collidable {
    public static DrawStyle DEFAULT_DRAW_STYLE = new DrawStyle.FillColorStyle("black");

    public final Shape relativeShape;
    @NotNull
    private DrawStyle drawStyle;

    /**
     * A game object that is represented by a {@link java.awt.Shape}.
     * The object will be drawn with a black fill color.
     *
     * @param centerX The center x-coordinate of the object.
     * @param centerY The center y-coordinate of the object.
     * @param relativeShape The Shape that represents the object where the origin is the center of the object.
     */
    public ShapedGameObject(float centerX, float centerY, Shape relativeShape) {
        this(centerX, centerY, relativeShape, null);
    }

    /**
     * A game object that is represented by a {@link java.awt.Shape}.
     *
     * @param centerX The center x-coordinate of the object.
     * @param centerY The center y-coordinate of the object.
     * @param relativeShape The Shape that represents the object where the origin is the center of the object.
     * @param drawStyle The style to draw the object with.
     */
    public ShapedGameObject(float centerX, float centerY, Shape relativeShape, @Nullable DrawStyle drawStyle) {
        super(centerX, centerY);
        this.relativeShape = relativeShape;
        this.drawStyle = Objects.requireNonNullElse(drawStyle, DEFAULT_DRAW_STYLE);
    }

    @Override
    public boolean containsPoint(int x, int y) {
        return relativeShape.contains(x - getCenterX(), y - getCenterY());
    }

    @Override
    public boolean containsRect(Rectangle2D rect) {
        float x = (int) rect.getX() - getCenterX();
        float y = (int) rect.getY() - getCenterY();

        return relativeShape.intersects(x, y, rect.getWidth(), rect.getHeight());
    }

    @Override
    public boolean shouldCollide() {
        return getState() == State.PASSIVE;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.translate(getCenterX(), getCenterY());
        getDrawStyle().draw(graphics2D, this.relativeShape);
        graphics2D.translate(-getCenterX(), -getCenterY());
    }

    @Override
    public void tick() {

    }

    public void setColor(String color) {
        setDrawStyle(new DrawStyle.FillColorStyle(color));
    }

    public void setImage(String path) {
        setDrawStyle(new DrawStyle.ImagedStyle(path));
    }

    public void setDrawStyle(DrawStyle drawStyle) {
        this.drawStyle = Objects.requireNonNullElse(drawStyle, DEFAULT_DRAW_STYLE);
    }

    public @NotNull DrawStyle getDrawStyle() {
        return drawStyle;
    }
}
