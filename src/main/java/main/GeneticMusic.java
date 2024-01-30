package main;

import domain.music.SongGeneration;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 29.08.2023, Di.
 */
public class GeneticMusic {

    public static void main(String[] args) {
        var generation = new SongGeneration(5, 5, 0.1, 0.1);

        for (int i = 0; i < 10; i++) {
            System.out.println(STR."Generation \{generation.getGenCount()}");
            generation.evolve();
            System.out.println();
        }
    }

}
