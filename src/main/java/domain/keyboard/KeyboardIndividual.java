package domain.keyboard;

import domain.genetic.Individual;
import domain.random.Randomizer;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 25.08.2023, Fr.
 */
public class KeyboardIndividual extends Individual<KeyboardIndividual> {

    private final Language language;
    private final KeyboardLayout layout;

    public KeyboardIndividual(Language language) {
        this.language = language;
        this.layout = new KeyboardLayout(language);
    }

    @Override
    public void init() {
        Randomizer.randomizeKeyboardLayout(layout);
    }

    /**
     * @param other individual to cross over with
     * @return child of this and other individual
     */
    @Override
    public KeyboardIndividual crossover(KeyboardIndividual other) {
        var child = new KeyboardIndividual(language);
        var usedLetters = new boolean[language.getAlphabet().length()];
        var fitterIndividual = (getFitness() > other.getFitness()) ? this : other;
        var unfitterIndividual = (getFitness() > other.getFitness()) ? other : this;
        double combinedFitness = getFitness() + other.getFitness();
        double fitterIndividiualProbability = fitterIndividual.getFitness() / combinedFitness;

        for (int j = 0; j < language.getRowCount(); j++) {
            for (int i = 0; i < language.getColumnCount(j); i++) {
                char letter;

                if (Randomizer.RANDOM.nextDouble() < fitterIndividiualProbability) {
                    letter = getLetter(fitterIndividual, j, i, usedLetters);
                } else {
                    letter = getLetter(unfitterIndividual, j, i, usedLetters);
                }

                usedLetters[language.getLetterIndex(letter)] = true;
                child.layout.set(j, i, letter);
            }
        }

        return child;
    }

    private static char getLetter(KeyboardIndividual individual, int row, int col, boolean[] usedLetters) {
        char letter = individual.layout.get(row, col);

        if (usedLetters[individual.language.getLetterIndex(letter)]) {
            letter = individual.language.getAlphabet().charAt(Randomizer.randomFalseIndex(usedLetters));
        }

        return letter;
    }


    @Override
    public void mutate(double mutationRate) {
        if (Randomizer.RANDOM.nextDouble() < mutationRate) {
            char letter1 = Randomizer.randomLetter(language);
            char letter2;

            do {
                letter2 = Randomizer.randomLetter(language);
            } while (letter1 == letter2);

            layout.swap(letter1, letter2);
            isFitnessOutdated = true;
        }
    }

    @Override
    protected double calcFitness() {
        var chars = SampleText.getChars(language);
        double lowestDistance = chars.length - 1;
        double travelDistance = layout.totalTravelDistance(chars);
        return lowestDistance / travelDistance;
    }

    @Override
    public String toString() {
        return layout.toString();
    }

    public KeyboardLayout getLayout() {
        return layout;
    }

}
