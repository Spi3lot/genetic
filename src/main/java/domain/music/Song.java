package domain.music;

import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 29.08.2023, Di.
 */
@Getter
public class Song {

    private final Note[] notes;

    public Song(int noteCount) {
        this.notes = new Note[noteCount];
    }

    public int getNoteCount() {
        return notes.length;
    }

    public double getDuration() {
        var latestNote = Arrays.stream(notes)
                .max(Comparator.comparingDouble(note -> note.getStartTime() + note.getDuration()))
                .orElseThrow();

        return latestNote.getStartTime() + latestNote.getDuration();
    }

    public void setNote(int index, Note note) {
        notes[index] = note;
    }

}
