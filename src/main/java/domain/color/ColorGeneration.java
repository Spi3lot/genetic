package domain.color;

import domain.genetic.Generation;
import main.GeneticAlgorithm;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 17.08.2023, Do.
 */
public class ColorGeneration extends Generation<ColorIndividual> {

    private int targetColor;

    public ColorGeneration(GeneticAlgorithm ga, int size, float mutationRate, float elitismRate) {
        super(ga, size, mutationRate, elitismRate);
    }

    @Override
    public void init() {
        for (int i = 0; i < size; i++) {
            individuals[i] = new ColorIndividual(ga, targetColor);
            individuals[i].init();
        }
    }

    public void setTargetColor(int color) {
        this.targetColor = color;
    }

}
