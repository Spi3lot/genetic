package main;


import domain.keyboard.KeyboardGeneration;
import domain.keyboard.KeyboardIndividual;
import domain.keyboard.KeyboardLayout;
import domain.keyboard.Language;
import domain.random.Randomizer;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 25.08.2023, Fr.
 */
public class KeyboardEvolver {

    private static final Language LANGUAGE = Language.ENGLISH;
    private static final int MAX_GENERATIONS = 1000;
    private static final int POPULATION_SIZE = 50;
    private static final double MUTATION_RATE = 0.1;
    private static final double ELITISM_RATE = 0.1;

    public static void main(String[] args) {
        var generation = new KeyboardGeneration(LANGUAGE, POPULATION_SIZE, MUTATION_RATE, ELITISM_RATE);

        for (int i = 0; i < MAX_GENERATIONS; i++) {
            if (i % 25 == 0) {
                printFittest(generation);
            }

            generation.evolve();
        }

        printFittest(generation);
    }

    private static void printFittest(KeyboardGeneration generation) {
        var fittest = generation.getFittestIndividual();
        System.out.println("Generation: " + generation.getGenCount());
        System.out.println("Fitness: " + fittest.getFitness());
        System.out.println(fittest.getLayout());
    }

    public static void test() {
        var ki1 = new KeyboardIndividual(LANGUAGE);
        var ki2 = new KeyboardIndividual(LANGUAGE);
        ki1.init();
        ki2.init();
        System.out.println(ki1.getFitness());
        System.out.println(ki1.getLayout());
        System.out.println(ki2.getFitness());
        System.out.println(ki2.getLayout());
        System.out.println(ki1.crossover(ki2).getLayout());
    }

}
