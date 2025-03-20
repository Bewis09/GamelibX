package gamelibx;

import gamelibx.resource_loader.ResourceLoader;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.BufferedInputStream;
import java.util.ArrayList;

public class Sound {
    private final String path;
    private final ArrayList<Clip> clips = new ArrayList<>();

    public Sound(String path) {
        this.path = path;
    }

    public Clip play() {
        return play(100);
    }

    public Clip play(int volume) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(ResourceLoader.getResourceAsStream(path)));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float volumeInDecibels = 20f * (float) Math.log10(volume / 100.0);
            volumeControl.setValue(volumeInDecibels);
            clip.start();
            clip.drain();
            this.clips.add(clip);
            return clip;
        } catch (Exception ignored) {
            return null;
        }
    }

    public void close(Clip clip) {
        clip.stop();
        clip.close();
        clips.remove(clip);
    }

    public boolean anyActive() {
        return clips.stream().anyMatch(Clip::isActive);
    }

    public boolean anyPlaying() {
        return clips.stream().anyMatch(Clip::isRunning);
    }

    public void stopAll() {
        clips.forEach(Clip::stop);
    }

    public void clearStopped() {
        for (Clip clip : clips) {
            if (!clip.isRunning()) {
                clip.close();
            }
        }

        clips.removeIf(clip -> !clip.isRunning());
    }

    public void clearInactive() {
        for (Clip clip : clips) {
            if (!clip.isActive()) {
                clip.stop();
                clip.close();
            }
        }

        clips.removeIf(clip -> !clip.isRunning());
    }

    public void closeAll() {
        for (Clip clip : clips) {
            clip.stop();
            clip.close();
        }

        clips.clear();
    }
}
