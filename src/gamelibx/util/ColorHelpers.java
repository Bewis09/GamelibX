package gamelibx.util;

import java.awt.*;
import java.lang.reflect.Field;

public class ColorHelpers {
    public static Color getColor(String color) {
        Color c;
        try {
            Field field = Class.forName("java.awt.Color").getField(color);
            c = (Color)field.get(null);
        } catch (Exception e) {
            try {
                c = Color.decode(color);
            } catch (NumberFormatException numberFormatException) {
                c = Color.BLACK;
            }
        }
        return c;
    }
}
