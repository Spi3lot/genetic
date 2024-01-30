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
        super(size, mutationRate, elitismPercentage, ImageIndividual[]::new, () -> new ImageIndividual(ga, image));
    }

}
