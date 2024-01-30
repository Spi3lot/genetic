package domain.random;

import domain.keyboard.KeyboardLayout;
import domain.keyboard.Language;
import domain.music.Note;
import domain.music.Song;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 21.08.2023, Mo.
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Randomizer {

    public static final Random RANDOM = new Random();

    public static int nextColor() {
        return 0xFF000000 | RANDOM.nextInt(0x01000000);
    }

    public static <T> T randomWithout(T without, Supplier<T> randomSupplier) {
        T random;

        do {
            random = randomSupplier.get();
        } while (random == without);

        return random;
    }

    public static int randomHigher(int high, double exponent) {
        return (int) Math.pow(RANDOM.nextDouble(Math.pow(high, exponent)), 1 / exponent);
    }

    public static int randomLower(int high, double exponent) {
        return (int) (Math.pow(RANDOM.nextDouble(), exponent) * high);
    }

    public static void randomizeKeyboardLayout(KeyboardLayout layout) {
        var letters = layout.getLanguage()
                .getLetters();

        for (int j = 0; j < layout.getLanguage().getRowCount(); j++) {
            for (int i = 0; i < layout.getLanguage().getColumnCount(j); i++) {
                layout.set(j, i, choose(letters, true));
            }
        }
    }

    public static char randomLetter(Language language) {
        return randomLetter(language.getAlphabet());
    }

    public static char randomLetter(String s) {
        return s.charAt(RANDOM.nextInt(s.length()));
    }

    public static int randomFalseIndex(boolean[] array) {
        int idx;

        do {
            idx = RANDOM.nextInt(array.length);
        } while (array[idx]);

        return idx;
    }

    public static void randomizeSong(Song song) {
        for (int i = 0; i < song.getNoteCount(); i++) {
            song.setNote(i, randomNote(song.getNoteCount()));
        }
    }

    public static Note randomNote(int noteCount) {
        return new Note(
                RANDOM.nextDouble(100, 1000),
                RANDOM.nextDouble(0.1, 1),
                RANDOM.nextDouble(0, noteCount),
                RANDOM.nextDouble(0.1, 1)
        );
    }

    public static <T> T choose(List<T> options, boolean remove) {
        var choice = options.get(RANDOM.nextInt(options.size()));

        if (remove) {
            options.remove(choice);
        }

        return choice;
    }

}
