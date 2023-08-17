package main;

import domain.color.ColorGeneration;
import processing.core.PApplet;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 14.08.2023, Mo.
 */
public class GeneticAlgorithm extends PApplet {

    private static final int W = 192;
    private static final int H = 256;
    private static final int GENERATION_SIZE = 5;
    private static final float MUTATION_RATE = 0.1f;
    private static final float ELITISM_RATE = 0.1f;
    private final ColorGeneration[] generations = new ColorGeneration[W * H];

    public static void main(String[] args) {
        PApplet.main("main.GeneticAlgorithm");
    }

    @Override
    public void settings() {
        size(W, H);
    }

    @Override
    public void setup() {
        noStroke();

        var image = loadImage("src/main/resources/shrek.jpg");
        image.resize(W, H);

        for (int i = 0; i < generations.length; i++) {
            generations[i] = new ColorGeneration(this, GENERATION_SIZE, MUTATION_RATE, ELITISM_RATE);
            generations[i].setTargetColor(image.pixels[i]);
            generations[i].init();
        }
    }

    @Override
    public void draw() {
        loadPixels();

        for (int i = 0; i < generations.length; i++) {
            var generation = generations[i];
            var individual = generation.getFittestIndividual();
            var color = individual.getColorChromosome().getColor();
            pixels[i] = color;
            generation.evolve();
        }

        updatePixels();
    }

}
