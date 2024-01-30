package domain.genetic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 15.08.2023, Di.
 */
public abstract class Individual<T extends Individual<T>> {

    private static BufferedReader reader;
    protected double fitness;
    protected boolean isFitnessOutdated = true;

    protected static double readFitnessFromUserInput() {
        if (reader == null) {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }

        return readFitness(reader);
    }

    private static double readFitness(BufferedReader reader) {
        while (true) {
            try {
                System.out.print("Estimated fitness: ");
                return Double.parseDouble(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public abstract void init();

    public abstract T crossover(T other);

    public abstract void mutate(double mutationRate);

    protected abstract double calcFitness();

    public final double getFitness() {
        if (isFitnessOutdated) {
            isFitnessOutdated = false;
            fitness = calcFitness();
        }

        return fitness;
    }

}
