package domain.genetic;

import domain.random.Randomizer;
import processing.core.PApplet;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 15.08.2023, Di.
 */
public abstract class Generation<T extends Individual<T>> {

    private static final double PROBABILITY_EXPONENT = 10.0;
    protected final int size;
    protected final int elitismCount;
    protected final double mutationRate;
    private final IntFunction<T[]> arrayConstructor;
    private final Supplier<Integer> randomIndexSupplier;
    protected T[] individuals;
    private int genCount;

    protected Generation(int size, double mutationRate, double elitismPercentage, IntFunction<T[]> arrayConstructor, Supplier<T> individualSupplier) {
        this.size = size;
        this.elitismCount = PApplet.constrain((int) (size * elitismPercentage), 1, size - 1);
        this.mutationRate = mutationRate;
        this.arrayConstructor = arrayConstructor;
        this.randomIndexSupplier = () -> Randomizer.randomHigher(size, PROBABILITY_EXPONENT);
        this.individuals = arrayConstructor.apply(size);

        for (int i = 0; i < size; i++) {
            individuals[i] = individualSupplier.get();
            individuals[i].init();
        }
    }

    public void evolve() {
        var sorted = Arrays.stream(individuals)
                .sorted(Comparator.comparing(Individual::getFitness))  // ascending
                .toArray(arrayConstructor);

        individuals = sorted.clone();

        for (int i = 0; i < size - elitismCount; i++) {
            int motherIdx = randomIndexSupplier.get();
            int fatherIdx = Randomizer.randomWithout(motherIdx, randomIndexSupplier);
            individuals[i] = sorted[motherIdx].crossover(sorted[fatherIdx]);
            individuals[i].mutate(mutationRate);
        }

        genCount++;
    }

    public T getFittestIndividual() {
        return Arrays.stream(individuals)
                .max(Comparator.comparing(Individual::getFitness))
                .orElseThrow();
    }

    public final T[] getIndividuals() {
        return individuals;
    }

    public final int getGenCount() {
        return genCount;
    }

}
