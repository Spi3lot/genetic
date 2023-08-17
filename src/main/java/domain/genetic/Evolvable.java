package domain.genetic;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 15.08.2023, Di.
 */
public interface Evolvable {

    void mutate();

    Evolvable crossover(Evolvable other);

    float calcFitness();

}
