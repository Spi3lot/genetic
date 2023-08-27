package domain.color;

import domain.genetic.Generation;
import main.GeneticAlgorithm;
import processing.core.PImage;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 17.08.2023, Do.
 */
public class ImageGeneration extends Generation<ImageIndividual> {

    public ImageGeneration(GeneticAlgorithm ga, PImage image, int size, float mutationRate, float elitismPercentage) {
        super(ImageIndividual[]::new, size, mutationRate, elitismPercentage);

        for (int i = 0; i < size; i++) {
            individuals[i] = new ImageIndividual(ga, image);
            individuals[i].init();
        }
    }

}
