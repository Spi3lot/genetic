package domain.imitator;

import domain.genetic.Generation;

import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * @author Emilio Zottel (5AHIF)
 * @since 30.01.2024, Di.
 */
public class ImitatorGeneration extends Generation<ImitatorIndividual> {

    public ImitatorGeneration(int size, double mutationRate, double elitismPercentage) {
        super(size, mutationRate, elitismPercentage, ImitatorIndividual[]::new, () -> new ImitatorIndividual(65536));
    }

}
