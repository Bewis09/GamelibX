package gamelibx;

import gamelibx.game.GameObject;
import gamelibx.util.ColorHelpers;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Text extends GameObject {
    private String text;
    private Color color;
    private Font font;

    public Text(float centerX, float centerY) {
        this(centerX, centerY, null);
    }

    public Text(float centerX, float centerY, String text) {
        this(centerX, centerY, text, "black");
    }

    public Text(float centerX, float centerY, String text, String color) {
        this(centerX, centerY, text, color, 24, false, false);
    }

    public Text(float centerX, float centerY, String text, boolean bold, boolean italic) {
        this(centerX, centerY, text, "black", 24, bold, italic);
    }

    public Text(float centerX, float centerY, String text, int size) {
        this(centerX, centerY, text, size, false, false);
    }

    public Text(float centerX, float centerY, String text, int size, boolean bold, boolean italic) {
        this(centerX, centerY, text, "black", size, bold, italic);
    }

    public Text(float centerX, float centerY, String text, String color, int size) {
        this(centerX, centerY, text, color, size, false, false);
    }

    public Text(float centerX, float centerY, String text, String color, int size, boolean bold, boolean italic) {
        this(centerX, centerY, text, color, "Arial", size, bold, italic);
    }

    public Text(float centerX, float centerY, String text, String color, String fontName, int size, boolean bold, boolean italic) {
        this(centerX, centerY, text, ColorHelpers.getColor(color), new Font(fontName, (bold ? Font.BOLD : Font.PLAIN) | (italic ? Font.ITALIC : Font.PLAIN), size));
    }

    public Text(float centerX, float centerY, String text, Color color, Font font) {
        super(centerX, centerY);
        this.text = text;
        this.color = color;
        this.font = font;
    }

    public final void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public final void setColor(String color) {
        this.color = ColorHelpers.getColor(color);
    }

    public final void setFont(@Nullable Font font) {
        this.font = font;
    }

    public Color getColor() {
        return color;
    }

    public Font getFont() {
        return font;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(getFont());
        graphics2D.setColor(getColor());
        Rectangle2D bounds = graphics2D.getFontMetrics().getStringBounds(getText(), graphics2D);
        graphics2D.drawString(getText(), (int) (getCenterX() - bounds.getWidth() / 2), (int) (getCenterY() + bounds.getHeight() / 2));
    }

    @Override
    public void tick() {

    }
}
