package gamelibx.swing;

import gamelibx.Game;

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
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Game.getInstance().paint(g);
    }
}
