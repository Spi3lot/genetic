package main;

import domain.color.ImageGeneration;
import processing.core.PApplet;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 14.08.2023, Mo.
 */
public class GeneticAlgorithm extends PApplet {

    private static final int W = 192 / 2;
    private static final int H = 256 / 2;
    private static final int GENERATION_SIZE = 100;
    private static final float MUTATION_RATE = 0.1f;
    private static final float ELITISM_PERCENTAGE = 0.1f;
    private ImageGeneration generation;

    public static void main(String[] args) {
        PApplet.main(GeneticAlgorithm.class);
    }

    @Override
    public void settings() {
        size(W, H);
    }

    @Override
    public void setup() {
        var image = loadImage("src/main/resources/shrek.jpg");
        image.resize(W, H);
        generation = new ImageGeneration(this, image, GENERATION_SIZE, MUTATION_RATE, ELITISM_PERCENTAGE);
    }

    @Override
    public void draw() {
        var fittestIndividual = generation.getFittestIndividual();
        var fittestPixels = fittestIndividual.getPixels();

        if (generation.getGenCount() % 100 == 0) {
            System.out.println(STR."Generation: \{generation.getGenCount()} (\{fittestIndividual.getFitness()})");
        }

        loadPixels();
        System.arraycopy(fittestPixels, 0, pixels, 0, fittestPixels.length);
        updatePixels();
        generation.evolve();
    }

}
