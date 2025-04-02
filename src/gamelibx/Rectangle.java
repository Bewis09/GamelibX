package gamelibx;

import gamelibx.drawing.DrawStyle;
import gamelibx.game.ShapedGameObject;
import gamelibx.interfaces.Collidable;
import gamelibx.interfaces.Rectangular;
import org.jetbrains.annotations.Nullable;

import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class Rectangle extends ShapedGameObject implements Rectangular {
    private float velocityX = 0f;
    private float velocityY = 0f;
    private Float gravity = null;
    private float resistanceFactor = 0.99f;

    public Rectangle(float x, float y, float width, float height, @Nullable DrawStyle drawStyle) {
        super(x + width / 2f, y + height / 2f, new Rectangle2D.Float(-width / 2f, -height / 2f, width, height), drawStyle);
    }

    public Rectangle(float x, float y, float width, float height) {
        this(x, y, width, height, null);
    }

    public float getWidth() {
        return (float) ((Rectangle2D.Float) relativeShape).getWidth();
    }

    public float getHeight() {
        return (float) ((Rectangle2D.Float) relativeShape).getHeight();
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Float(getCenterX() - getWidth() / 2f, getCenterY() - getHeight() / 2f, getWidth(), getHeight());
    }

    public void setGravity(Float gravity) {
        this.gravity = gravity;
    }

    public float getGravity() {
        return Objects.requireNonNullElse(gravity, Game.getInstance().getDefaultGravity());
    }

    public void setResistanceFactor(float resistanceFactor) {
        this.resistanceFactor = resistanceFactor;
    }

    public float getResistanceFactor() {
        return resistanceFactor;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public void setVelocity(float velocityX, float velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public boolean isOnGround() {
        centerY += 1;

        for (Collidable collidable : Game.getInstance().getCollidables()) {
            if (collidable != this) {
                if (collidable.containsRect(this.getBounds())) {
                    centerY -= 1;
                    return true;
                }
            }
        }

        centerY -= 1;
        return false;
    }

    @Override
    public void tick() {
        if (getState() != State.ACTIVE) return;

        velocityY += getGravity();
        velocityX *= resistanceFactor;
        velocityY *= resistanceFactor;
        centerY += velocityY;
        centerX += velocityX;

        for (Collidable collidable : Game.getInstance().getCollidables()) {
            if (collidable == this || collidable.isHidden() || !collidable.containsRect(this.getBounds())) continue;

            collidable.onCollide(Game.getInstance());

            if (!collidable.shouldCollide()) continue;

            float velocitySquared = velocityX * velocityX + velocityY * velocityY;
            float originalHeight = centerY;

            for (float i = 0; i * i < velocitySquared * 8 && velocityY != 0; i += 1f) {
                centerY -= Math.abs(velocityY) / velocityY;

                if (!collidable.containsRect(this.getBounds())) break;
            }

            if (collidable.containsRect(this.getBounds())) {
                centerY = originalHeight;

                float originalWidth = centerX;

                for (float i = 0; i * i < velocitySquared * 8 && velocityX != 0; i += 1f) {
                    centerX -= Math.abs(velocityX) / velocityX;

                    if (!collidable.containsRect(this.getBounds())) break;
                }

                if (collidable.containsRect(this.getBounds())) {
                    centerX = originalWidth;

                    if (Math.max(Math.abs(velocityX), Math.abs(velocityY)) > 0) {
                        float stepX = velocityX / Math.max(Math.abs(velocityX), Math.abs(velocityY));
                        float stepY = velocityY / Math.max(Math.abs(velocityX), Math.abs(velocityY));

                        for (int i = 0; i < Math.max(Math.abs(velocityX), Math.abs(velocityY)); i++) {
                            centerX -= stepX;
                            centerY -= stepY;

                            if (!collidable.containsRect(this.getBounds())) break;
                        }

                        velocityY = 0;
                    }
                }

                velocityX = 0;
            } else {
                velocityY = 0;
            }
        }
    }
}
