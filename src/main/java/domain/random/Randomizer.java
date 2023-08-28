package domain.random;

import domain.keyboard.KeyboardLayout;
import domain.keyboard.Language;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 21.08.2023, Mo.
 */
public class Randomizer {

    public static final Random RANDOM = new Random();

    private Randomizer() {
    }

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
        for (char letter : layout.getLanguage().getAlphabet().toCharArray()) {
            int row;
            int col;

            do {
                row = RANDOM.nextInt(layout.getLanguage().getRowCount());
                col = RANDOM.nextInt(layout.getLanguage().getColumnCount(row));
            } while (layout.get(row, col) != 0);

            layout.set(row, col, letter);
        }
    }

    public static char randomLetter(Language language) {
        return randomLetter(language.getAlphabet());
    }

    public static char randomLetter(String s) {
        return s.charAt(RANDOM.nextInt(s.length()));
    }

    public static int randomFalseIndex(boolean[] arr) {
        int idx;

        do {
            idx = RANDOM.nextInt(arr.length);
        } while (arr[idx]);

        return idx;
    }

}
