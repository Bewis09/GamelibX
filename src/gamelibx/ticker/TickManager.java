package gamelibx.ticker;

import gamelibx.Game;

public class TickManager {
    private static final int MAX_TICK_SKIP = 5;
    private static final int TARGETED_TICKS = 60;

    private static boolean running = false;
    private static boolean tickRunning = false;

    public static void run() {
        new Thread(() -> {
            if(running) {
                throw new IllegalStateException("TickManager is already running.");
            }

            long nextRenderTick = System.currentTimeMillis();
            running = true;

            while (running) {
                if (System.currentTimeMillis() > nextRenderTick) {
                    nextRenderTick = System.currentTimeMillis() + 1000 / TARGETED_TICKS;
                    Game.getInstance().onRender();
                }
            }
        }, "Game Thread").start();
    }

    public static boolean isRunning() {
        return tickRunning;
    }

    public static void setTickRate() {
        TickManager.tickRunning = true;
    }

    public static void interrupt() {
        TickManager.tickRunning = false;
    }
}
