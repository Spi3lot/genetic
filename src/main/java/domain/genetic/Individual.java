package domain.genetic;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 15.08.2023, Di.
 */
public abstract class Individual<T extends Individual<?>> {

    protected double fitness;
    protected boolean isFitnessOutdated = true;

    public abstract void init();

    public abstract T crossover(T other);

    public abstract void mutate(double mutationRate);

    protected abstract double calcFitness();

    public double getFitness() {
        if (isFitnessOutdated) {
            isFitnessOutdated = false;
            fitness = calcFitness();
        }

        return fitness;
    }

}
