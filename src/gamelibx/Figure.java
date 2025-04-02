package gamelibx;

import gamelibx.drawing.DrawStyle;
import org.jetbrains.annotations.Nullable;

/**
 * This is just to make clear what class should be used to create a figure. {@link Rectangle} is equally good.
 */
public class Figure extends Rectangle {
    public Figure(float x, float y, float width, float height, @Nullable DrawStyle drawStyle) {
        super(x, y, width, height, drawStyle);
    }

    public Figure(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
}
