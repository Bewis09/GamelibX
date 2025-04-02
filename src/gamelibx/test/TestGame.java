package gamelibx.test;

import gamelibx.Game;
import gamelibx.Rectangle;
import gamelibx.Sound;
import gamelibx.Text;

import java.awt.event.KeyEvent;

public class TestGame extends Game {
    Rectangle player = new Rectangle(50, 400, 50, 50);
    Rectangle platform1 = new Rectangle(0, 470, 300, 100);
    Rectangle platform2 = new Rectangle(500, 470, 500, 100);
    Rectangle barrier = new Rectangle(800, 370, 50, 100);
    Text text = new Text(400, 300, "Hello, World!", true, false);
    Sound death;

    public TestGame() {
        super("Test Game", 800, 600);

        death = new Sound("gamelibx/test/resources/death.wav");

        setDefaultGravity(0.2f);

        player.makeActive();
        platform1.makePassive();
        platform2.makePassive();
        barrier.makePassive();

        platform1.setRepeatingImage("gamelibx/test/resources/grass.png");
        platform2.setRepeatingImage("gamelibx/test/resources/grass.png");
        player.setImage("gamelibx/test/resources/player.gif");

        setBackgroundImage("gamelibx/test/resources/background.png");

        startTicker();
    }

    public static void main(String[] args) {
        new TestGame();
    }

    @Override
    public void tick() {
        player.setVelocityX(3);
        setGameX((Math.max(0,player.getCenterX() - 100) + getGameX() * 4) / 5);

        if (player.getCenterY() > 600) {
            player.setCenterY(425);
            player.setCenterX(75);
            player.setVelocity(0, 0);
            death.play();
        }
    }

    @Override
    public void keyPressed(char c, int code) {
        if (code == KeyEvent.VK_SPACE && player.isOnGround()) {
            player.setVelocityY(-8);
        }
    }

    @Override
    public void mouseClicked(int x, int y, int button) {
        if (player.isOnGround()) {
            player.setVelocityY(-8);
        }
    }
}
