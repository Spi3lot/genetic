package domain.genetic;

import main.GeneticAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 15.08.2023, Di.
 */
public abstract class Generation<T extends Individual> {

    protected final GeneticAlgorithm ga;
    protected final int size;
    protected final float mutationRate;
    protected final float elitismRate;
    protected Individual[] individuals;

    protected Generation(GeneticAlgorithm ga, int size, float mutationRate, float elitismRate) {
        this.ga = ga;
        this.size = size;
        this.mutationRate = mutationRate;
        this.elitismRate = elitismRate;
        this.individuals = new Individual[size];
    }

    public abstract void init();

    public void evolve() {
        var sorted = Arrays.stream(individuals)
                .sorted(Comparator.comparing(Evolvable::calcFitness))
                .toArray(Individual[]::new);

        for (int i = 0; i < size; i++) {
            var individual = sorted[i];

            if (ga.random(1) < elitismRate) {
                individuals[i] = individual;
            } else {
                int partnerIndex = randomWithout(size, i);
                individuals[i] = (Individual) individual.crossover(sorted[partnerIndex]);

                if (ga.random(1) < mutationRate) {
                    individual.mutate();
                }
            }
        }
    }

    private int randomWithout(int high, int without) {
        int random;

        do {
            random = (int) ga.random(0, high);
        } while (random == without);

        return random;
    }

    public final Individual[] getIndividuals() {
        return individuals;
    }

    public final T getIndividual(int index) {
        return (T) individuals[index];
    }

    public final T getFittestIndividual() {
        return (T) Arrays.stream(individuals)
                .max(Comparator.comparing(Evolvable::calcFitness))
                .orElseThrow();
    }

}
