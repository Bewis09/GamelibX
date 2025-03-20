package gamelibx.drawing;

import gamelibx.game.ShapedGameObject;
import gamelibx.resource_loader.ResourceLoader;
import gamelibx.util.ColorHelpers;

import javax.swing.*;
import java.awt.*;

public interface DrawStyle {
    void draw(Graphics2D graphics2D, Shape shape);

    class FillColorStyle implements DrawStyle {
        public final Color color;

        public FillColorStyle(String color) {
            this.color = ColorHelpers.getColor(color);
        }

        @Override
        public void draw(Graphics2D graphics2D, Shape shape) {
            graphics2D.setColor(color);
            graphics2D.fill(shape);
        }
    }

    record ImagedStyle(Image image) implements DrawStyle {
        public ImagedStyle(String path) {
            this(new ImageIcon(ResourceLoader.getResource(path)).getImage());
        }

        @Override
        public void draw(Graphics2D graphics2D, Shape shape) {
            graphics2D.drawImage(image, (int) ( shape.getBounds().getX()),
                    (int) ( shape.getBounds().getY()),
                    (int) ( shape.getBounds().getWidth()),
                    (int) ( shape.getBounds().getHeight()), null);
        }
    }
}
