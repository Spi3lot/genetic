package domain.color;

import domain.genetic.Individual;
import domain.random.Randomizer;
import lombok.Getter;
import main.GeneticAlgorithm;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 15.08.2023, Di.
 */
public class ImageIndividual extends Individual<ImageIndividual> {

    private final GeneticAlgorithm ga;
    private final PImage image;

    @Getter
    private final int[] pixels;

    public ImageIndividual(GeneticAlgorithm ga, PImage image) {
        this.ga = ga;
        this.image = image;
        this.pixels = new int[image.width * image.height];
    }

    private static PVector colorToVector(int color) {
        return new PVector(color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF);
    }

    @Override
    public void init() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = Randomizer.nextColor();
        }
    }

    @Override
    public ImageIndividual crossover(ImageIndividual o) {
        var child = new ImageIndividual(ga, image);
        double difference = o.getFitness() - getFitness();
        double t = Math.copySign(Math.pow(Math.abs(difference), 0.2), difference);

        for (int i = 0; i < pixels.length; i++) {
            child.pixels[i] = ga.lerpColor(pixels[i], o.pixels[i], (float) t);
        }

        return child;
    }

    @Override
    public void mutate(double mutationRate) {
        for (int i = 0; i < pixels.length; i++) {
            if (Randomizer.RANDOM.nextDouble() < mutationRate) {
                pixels[i] = ga.lerpColor(pixels[i], Randomizer.nextColor(), (float) (1 - getFitness()));
            }
        }

        isFitnessOutdated = true;
    }

    @Override
    public double calcFitness() {
        double fitness = 0;

        for (int i = 0; i < pixels.length; i++) {
            float diff = colorToVector(pixels[i]).dist(colorToVector(image.pixels[i])) / (255 * PApplet.sqrt(3));
            fitness += (1 - diff) * (1 - diff);  // harder to get better fitness
            //fitness += 1 - diff * diff;  // easier to get better fitness
        }

        return fitness / pixels.length;
    }

}
