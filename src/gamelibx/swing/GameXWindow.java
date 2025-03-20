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

        setBounds(
            (int) (getToolkit().getScreenSize().getWidth() / 2 - width / 2f),
            (int) (getToolkit().getScreenSize().getHeight() / 2 - height / 2f),
            width,
            height
        );

        setVisible(true);

        GameXPanel panel = new GameXPanel();
        this.add(panel);

        addKeyListener(Game.getInstance());
        addMouseListener(Game.getInstance());
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
