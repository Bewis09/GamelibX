package gamelibx.drawing;

import gamelibx.Rectangle;
import gamelibx.resource_loader.ResourceLoader;
import gamelibx.util.ColorHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

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

    record RepeatingImagedStyle(TexturePaint paint) implements DrawStyle {
        public RepeatingImagedStyle(String path, Rectangle2D anchor) {
            this(new TexturePaint(toBufferedImage(new ImageIcon(ResourceLoader.getResource(path)).getImage()), anchor));
        }

        @Override
        public void draw(Graphics2D graphics2D, Shape shape) {
            graphics2D.setPaint(paint);
            graphics2D.fill(shape);
        }

        public static BufferedImage toBufferedImage(Image img)
        {
            BufferedImage image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

            Graphics2D bGr = image.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();

            return image;
        }
    }
}
