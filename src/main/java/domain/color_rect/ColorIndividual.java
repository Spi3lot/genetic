package domain.color_rect;

import domain.genetic.Chromosome;
import domain.genetic.Evolvable;
import domain.genetic.Individual;
import main.GeneticAlgorithm;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 15.08.2023, Di.
 */
public class ColorIndividual extends Individual {

    private final int targetColor;

    public ColorIndividual(GeneticAlgorithm ga, int targetColor) {
        super(ga);
        this.targetColor = targetColor;
    }

    @Override
    public void init() {
        chromosomes = new Chromosome[]{
                new ColorChromosome(ga, targetColor)
        };
    }

    @Override
    public ColorIndividual crossover(Evolvable other) {
        var o = (ColorIndividual) other;
        var child = new ColorIndividual(ga, targetColor);
        child.crossParentChromosomes(this, o);
        return child;
    }

    @Override
    public float calcFitness() {
        return getColorChromosome().calcFitness();
    }

    public ColorChromosome getColorChromosome() {
        return (ColorChromosome) chromosomes[0];
    }

}
