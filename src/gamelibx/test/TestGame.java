package gamelibx.test;

import gamelibx.Game;

public class TestGame extends Game {
    public TestGame() {
        super("Test Game", 800, 600);
    }

    public static void main(String[] args) {
        new TestGame();
    }
}
