import javafx.scene.media.AudioClip;
import java.io.File;

/**
 * SoundHandler Class
 * @author Fredrik Skoglind
 */
public class AudioHandler {
    public AudioHandler() {}

    public void playSound(String filename) {
        playSoundFX(filename);
    }

    public void playSoundFX(String filename) {
        File file = new File(filename);
        AudioClip sound = new AudioClip(file.toURI().toString());
        sound.play();
    }
}
