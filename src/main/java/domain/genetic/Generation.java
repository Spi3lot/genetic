package domain.genetic;

import main.GeneticAlgorithm;
import processing.core.PApplet;

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
                .sorted(Comparator.comparing(Evolvable::calcFitness))  // ascending
                .toArray(Individual[]::new);

        for (int i = 0; i < size; i++) {
            if (ga.random(1) < elitismRate) {
                individuals[i] = sorted[i];
            } else {
                int motherIdx = (int) PApplet.sqrt(randomWithout(size * size, i));
                int fatherIdx = (int) PApplet.sqrt(randomWithout(size * size, i, motherIdx));
                individuals[i] = (Individual) sorted[motherIdx].crossover(sorted[fatherIdx]);

                if (ga.random(1) < mutationRate) {
                    individuals[i].mutate();
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

    private int randomWithout(int high, int without1, int without2) {
        int random;

        do {
            random = (int) ga.random(0, high);
        } while (random == without1 || random == without2);

        return random;
    }

    public final Individual[] getIndividuals() {
        return individuals;
    }

    @SuppressWarnings("unchecked")
    public final T getIndividual(int index) {
        return (T) individuals[index];
    }

    @SuppressWarnings("unchecked")
    public final T getFittestIndividual() {
        return (T) Arrays.stream(individuals)
                .max(Comparator.comparing(Evolvable::calcFitness))
                .orElseThrow();
    }

}
