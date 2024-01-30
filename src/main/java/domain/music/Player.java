package domain.music;

import lombok.NoArgsConstructor;
import processing.core.PApplet;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 29.08.2023, Di.
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Player {

    private static final AudioFormat FORMAT = new AudioFormat(44100, 8, 1, true, true);
    private static final SourceDataLine LINE;

    static {
        try {
            LINE = AudioSystem.getSourceDataLine(FORMAT);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public static void play(Song song) {
        var buffer = new byte[(int) (song.getDuration() * getFramesPerSecond())];

        for (var note : song.getNotes()) {
            int startFrame = (int) (note.getStartTime() * getFramesPerSecond());
            int frameCount = (int) (note.getDuration() * getFramesPerSecond());

            for (int i = startFrame; i < startFrame + frameCount; i++) {
                addToBuffer(buffer, i, sine(note.getFrequency(), i));
            }
        }

        play(buffer);
    }

    public static void play(Note note) {
        play(note.getFrequency(), note.getAmplitude(), note.getDuration());
    }

    private static void play(double frequency, double amplitude, double duration) {
        var buffer = new byte[(int) (duration * getFramesPerSecond())];

        for (int i = 0; i < buffer.length; i++) {
            byte value = (byte) (amplitude * sine(frequency, i));
            addToBuffer(buffer, i, value);
        }

        play(buffer);
    }

    private static void play(byte[] buffer) {
        try {
            LINE.open();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        LINE.start();
        LINE.write(buffer, 0, buffer.length);
        LINE.drain();
        LINE.close();
    }

    private static void addToBuffer(byte[] buffer, int index, byte value) {
        buffer[index] = (byte) PApplet.constrain(buffer[index] + value, -128, 127);
    }

    private static byte sine(double frequency, int i) {
        return asByte(Math.sin(i * frequency * Math.TAU / getFramesPerSecond()));
    }

    private static byte asByte(double value) {
        return (byte) (value * 127.0);
    }

    private static int getFramesPerSecond() {
        return (int) (FORMAT.getSampleRate() * FORMAT.getFrameSize());
    }

}
