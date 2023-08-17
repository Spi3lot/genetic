package domain.genetic;

import main.GeneticAlgorithm;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 14.08.2023, Mo.
 */
public abstract class Chromosome implements Evolvable {

    protected final GeneticAlgorithm ga;

    protected Chromosome(GeneticAlgorithm geneticAlgorithm) {
        this.ga = geneticAlgorithm;
    }

}
