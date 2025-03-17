package gamelibx.interfaces;

/**
 * An interface for objects that can be ticked.
 * Should be added to the Game class via the {@link gamelibx.Game#addTickable}} method.
 */
public interface Tickable {
    void tick();
}
