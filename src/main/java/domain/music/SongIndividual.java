package domain.music;

import domain.genetic.Individual;
import domain.random.Randomizer;
import lombok.Getter;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 29.08.2023, Di.
 */
@Getter
public class SongIndividual extends Individual<SongIndividual> {

    private final Song song;

    public SongIndividual(Song song) {
        this.song = song;
    }

    @Override
    public void init() {
        Randomizer.randomizeSong(song);
    }

    @Override
    public SongIndividual crossover(SongIndividual other) {
        var child = new SongIndividual(new Song(song.getNoteCount()));

        for (int i = 0; i < song.getNoteCount(); i++) {
            var note = song.getNotes()[i];
            var otherNote = other.getSong().getNotes()[i];

            child.song.setNote(i, new Note(
                    (note.getFrequency() + otherNote.getFrequency()) / 2,
                    (note.getAmplitude() + otherNote.getAmplitude()) / 2,
                    (note.getDuration() + otherNote.getDuration()) / 2,
                    (note.getStartTime() + otherNote.getStartTime()) / 2
            ));
        }

        return child;
    }

    @Override
    public void mutate(double mutationRate) {
        if (Randomizer.RANDOM.nextDouble() < mutationRate) {
            int noteIndex = Randomizer.RANDOM.nextInt(song.getNoteCount());
            var note = song.getNotes()[noteIndex];
            double random = Randomizer.RANDOM.nextDouble();

            switch (Randomizer.RANDOM.nextInt(4)) {
                case 0 -> note.setFrequency(100 + random * 900);
                case 1 -> note.setAmplitude(random);
                case 2 -> note.setDuration(random);
                case 3 -> note.setStartTime(random * song.getNoteCount());
                default -> throw new RuntimeException("This should never happen");
            }
        }
    }

    @Override
    protected double calcFitness() {
        Player.play(song);
        return readFitnessFromUserInput();
    }

}
