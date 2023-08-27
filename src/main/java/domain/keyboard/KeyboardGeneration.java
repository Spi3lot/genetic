package domain.keyboard;

import domain.genetic.Generation;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 26.08.2023, Sa.
 */
public class KeyboardGeneration extends Generation<KeyboardIndividual> {

    public KeyboardGeneration(Language language, int size, double mutationRate, double elitismPercentage) {
        super(KeyboardIndividual[]::new, size, mutationRate, elitismPercentage);

        for (int i = 0; i < size; i++) {
            individuals[i] = new KeyboardIndividual(language);
            individuals[i].init();
        }
    }

}
