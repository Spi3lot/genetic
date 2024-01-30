package domain.music;

import domain.genetic.Generation;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 29.08.2023, Di.
 */
public class SongGeneration extends Generation<SongIndividual> {

    public SongGeneration(int noteCount, int size, double mutationRate, double elitismPercentage) {
        super(size, mutationRate, elitismPercentage, SongIndividual[]::new, () -> new SongIndividual(new Song(noteCount)));
    }

}
