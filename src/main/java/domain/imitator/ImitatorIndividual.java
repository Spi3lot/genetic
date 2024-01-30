package domain.imitator;

import domain.genetic.Individual;

/**
 * @author Emilio Zottel (5AHIF)
 * @since 30.01.2024, Di.
 */
public class ImitatorIndividual extends Individual<ImitatorIndividual> {

    private final byte[] buffer;

    public ImitatorIndividual(int size) {
        this.buffer = new byte[size];
    }

    @Override
    public void init() {

    }

    @Override
    public ImitatorIndividual crossover(ImitatorIndividual other) {
        return null;
    }

    @Override
    public void mutate(double mutationRate) {

    }

    @Override
    protected double calcFitness() {
        return 0;
    }

}
