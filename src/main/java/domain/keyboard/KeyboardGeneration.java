package domain.keyboard;

import domain.genetic.Generation;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 26.08.2023, Sa.
 */
public class KeyboardGeneration extends Generation<KeyboardIndividual> {

    public KeyboardGeneration(Language language, int size, double mutationRate, double elitismPercentage) {
        super(size, mutationRate, elitismPercentage, KeyboardIndividual[]::new, () -> new KeyboardIndividual(language));
    }

}
