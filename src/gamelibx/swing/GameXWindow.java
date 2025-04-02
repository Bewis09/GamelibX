package gamelibx.swing;

import gamelibx.Game;
import gamelibx.ticker.TickManager;

import javax.swing.*;
import java.awt.*;

public class GameXWindow extends JFrame {
    public GameXWindow(String title, int width, int height) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle(title);

        GameXPanel panel = new GameXPanel();
        this.add(panel);

        panel.setPreferredSize(new Dimension(width, height));
        pack();

        setLocationRelativeTo(null);

        setVisible(true);

        addKeyListener(Game.getInstance());
        addMouseListener(Game.getInstance());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    private static class GameXPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (TickManager.isRunning())
                Game.getInstance().onTick();
            Game.getInstance().paint((Graphics2D) g);
        }
    }
}
