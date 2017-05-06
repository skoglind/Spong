import javax.sound.sampled.*;
import javax.sound.midi.*;
import java.io.File;

/**
 * SoundHandler Class
 * @author Fredrik Skoglind
 */
public class AudioHandler {
    public AudioHandler() { }

    public Clip playSound(String filename, int numberOfLoops) {
        try {
            File file = new File(filename);
            Clip audioClip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            audioClip.open(ais);
            audioClip.loop(numberOfLoops);

            return audioClip;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Sequencer playMusic(String filename) {
        try {
            File file = new File(filename);
            Sequence sequence = MidiSystem.getSequence(file);
            Sequencer sequencer = MidiSystem.getSequencer();

            sequencer.open();
            sequencer.setSequence(sequence);
            sequencer.start();

            return sequencer;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
