package domain.genetic;

import main.GeneticAlgorithm;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 15.08.2023, Di.
 */
public abstract class Individual implements Evolvable {

    protected final GeneticAlgorithm ga;
    protected Chromosome[] chromosomes;

    protected Individual(GeneticAlgorithm geneticAlgorithm) {
        this.ga = geneticAlgorithm;
    }

    public abstract void init();

    protected <T extends Individual> void crossParentChromosomes(T a, T b) {
        chromosomes = new Chromosome[a.chromosomes.length];

        for (int i = 0; i < chromosomes.length; i++) {
            chromosomes[i] = (Chromosome) a.chromosomes[i].crossover(b.chromosomes[i]);
        }
    }

    @Override
    public void mutate() {
        for (var chromosome : chromosomes) {
            chromosome.mutate();
        }
    }

}
