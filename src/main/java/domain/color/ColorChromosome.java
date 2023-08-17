package domain.color;

import domain.genetic.Chromosome;
import domain.genetic.Evolvable;
import main.GeneticAlgorithm;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 15.08.2023, Di.
 */
public class ColorChromosome extends Chromosome {

    private final int targetColor;
    private int color;

    public ColorChromosome(GeneticAlgorithm ga, int targetColor) {
        super(ga);
        this.targetColor = targetColor;
        this.color = randomColor();
    }

    @Override
    public void mutate() {
        color = ga.lerpColor(color, randomColor(), 1 - calcFitness());
    }

    @Override
    public ColorChromosome crossover(Evolvable other) {
        var o = (ColorChromosome) other;
        var child = new ColorChromosome(ga, targetColor);
        child.color = ga.lerpColor(color, o.color,  o.calcFitness() - calcFitness());
        return child;
    }

    @Override
    public float calcFitness() {
        var clr = new PVector(ga.red(color), ga.green(color), ga.blue(color));
        var target = new PVector(ga.red(targetColor), ga.green(targetColor), ga.blue(targetColor));
        return 1 - PVector.dist(clr, target) / (255 * PApplet.sqrt(3));
    }

    private int randomColor() {
        return ga.color(ga.random(256), ga.random(256), ga.random(256));
    }

    public int getColor() {
        return color;
    }

    public int getTargetColor() {
        return targetColor;
    }

}
