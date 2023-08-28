package domain.keyboard;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 25.08.2023, Fr.
 */
public enum Language {

    ENGLISH("QWERTYUIOPASDFGHJKLZXCVBNM", 10, 9, 7),
    GERMAN("QWERTZUIOPÜASDFGHJKLÖÄYXCVBNM", 11, 11, 7);

    private final String alphabet;
    private final char[] alphabetA;
    private final int[] columnCounts;
    private final Map<Character, Integer> letterPositions;

    Language(String alphabet, int... columnCounts) {
        this.alphabet = alphabet;
        alphabetA = alphabet.toCharArray();
        this.columnCounts = columnCounts;
        this.letterPositions = HashMap.newHashMap(alphabet.length());

        for (int i = 0; i < alphabet.length(); i++) {
            letterPositions.put(alphabet.charAt(i), i);
        }
    }

    public String getAlphabet() {
        return alphabet;
    }

    public int getRowCount() {
        return columnCounts.length;
    }

    public int getColumnCount(int row) {
        return columnCounts[row];
    }

    public int getLetterIndex(char letter) {
        return letterPositions.get(letter);
    }

    public char[] getAlphabetArray() {
        return alphabetA;
    }
}
