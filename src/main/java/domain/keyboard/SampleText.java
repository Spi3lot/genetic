package domain.keyboard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 25.08.2023, Fr.
 */
public class SampleText {

    private static final String[] texts = new String[Language.values().length];

    private SampleText() {
    }

    public static String get(Language language) {
        int idx = language.ordinal();

        if (texts[idx] == null) {
            try {
                texts[idx] = Files.readString(Path.of("src/main/resources/" + language.name() + ".txt"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return texts[idx];
    }

}
