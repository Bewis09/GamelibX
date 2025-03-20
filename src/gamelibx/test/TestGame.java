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
    Text text = new Text(400, 300, "Hello, World!", true, false);
    Sound death = new Sound("src/gamelibx/test/resources/death.wav");

    public TestGame() {
        super("Test Game", 800, 600);

        setDefaultGravity(0.2f);

        player.makeActive();
        platform1.makePassive();
        platform2.makePassive();

        platform1.setImage("src/gamelibx/test/resources/grass.png");
        platform2.setImage("src/gamelibx/test/resources/grass.png");
        player.setColor("0xFF77AA");

        startTicker();
    }

    public static void main(String[] args) {
        new TestGame();
    }

    @Override
    public void tick() {
        player.setVelocityX(3);

        if (player.getCenterY() > 600) {
            player.setCenterY(425);
            player.setCenterX(75);
            player.setVelocity(0, 0);
            death.play();
        }

        System.out.println(death.anyPlaying());
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
