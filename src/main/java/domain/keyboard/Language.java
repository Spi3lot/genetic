package domain.keyboard;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 25.08.2023, Fr.
 */
public enum Language {

    ENGLISH("QWERTYUIOPASDFGHJKLZXCVBNM", 10, 9, 7),
    GERMAN("QWERTZUIOPÜASDFGHJKLÖÄYXCVBNM", 11, 11, 7);

    private final String alphabet;
    private final int[] columnCounts;

    Language(String alphabet, int... columnCounts) {
        this.alphabet = alphabet;
        this.columnCounts = columnCounts;
    }

    public boolean hasLetter(char letter) {
        return alphabet.contains(Character.toUpperCase(letter) + "");
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

    public int[] getColumnCounts() {
        return columnCounts;
    }

}
