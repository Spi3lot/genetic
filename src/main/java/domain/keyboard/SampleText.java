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

    private SampleText() {
    }

    public static char[] getChars(Language language) {
        int idx = language.ordinal();

        if (TEXTS[idx] == null) {
            try {
                TEXTS[idx] = Files.readString(pathForLanguage(language))
                        .toUpperCase()
                        .replaceAll("[^" + language.getAlphabet() + "]", "")
                        .toCharArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return TEXTS[idx];
    }

    private static Path pathForLanguage(Language language) {
        return Path.of(PATH + language.name() + ".txt");
    }

}
