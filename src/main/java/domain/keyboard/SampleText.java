package domain.keyboard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 25.08.2023, Fr.
 */
public class SampleText {

    private static final String PATH = "src/main/resources/keyboard/";
    private static final char[][] TEXTS = new char[Language.values().length][];
    private static final int[] MIN_TRAVEL_DISTANCES = new int[Language.values().length];

    private SampleText() {
    }

    public static char[] getChars(Language language) {
        int idx = language.ordinal();

        if (TEXTS[idx] == null) {
            try {
                var text = Files.readString(pathForLanguage(language))
                        .toUpperCase()
                        .replaceAll("[^" + language.getAlphabet() + "]", "")
                        .toCharArray();

                TEXTS[idx] = text;

                for (int i = 0; i < text.length - 1; i++) {
                    if (text[i] != text[i + 1]) {
                        MIN_TRAVEL_DISTANCES[idx]++;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return TEXTS[idx];
    }

    public static int getMinTravelDistance(Language language) {
        return MIN_TRAVEL_DISTANCES[language.ordinal()];
    }

    private static Path pathForLanguage(Language language) {
        return Path.of(PATH + language.name() + ".txt");
    }

}
