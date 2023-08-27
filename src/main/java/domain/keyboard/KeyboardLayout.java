package domain.keyboard;

import java.util.Arrays;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 25.08.2023, Fr.
 */
public class KeyboardLayout {

    private final char[][] layout;
    private final Position[] positions;
    private final Language language;

    public KeyboardLayout(Language language) {
        this.language = language;
        this.positions = new Position[language.getAlphabet().length()];
        this.layout = new char[language.getRowCount()][0];

        for (int j = 0; j < layout.length; j++) {
            layout[j] = new char[language.getColumnCount(j)];
        }
    }

    public void swap(char letter1, char letter2) {
        var pos1 = getPosition(letter1);
        var pos2 = getPosition(letter2);

        char temp = layout[pos1.row()][pos1.col()];
        layout[pos1.row()][pos1.col()] = layout[pos2.row()][pos2.col()];
        layout[pos2.row()][pos2.col()] = temp;

        int letter1Index = language.getAlphabet().indexOf(letter1);
        int letter2Index = language.getAlphabet().indexOf(letter2);
        var tempPos = positions[letter1Index];
        positions[letter1Index] = positions[letter2Index];
        positions[letter2Index] = tempPos;
    }

    public double totalTravelDistance(String text) {
        double dist = 0;

        for (int i = 0; i < text.length() - 1; i++) {
            dist += distance(text.charAt(i), text.charAt(i + 1));
        }

        return dist;
    }

    public double distance(char letter1, char letter2) {
        if (!language.hasLetter(letter1) || !language.hasLetter(letter2)) {
            return 0;
        }

        return Position.distance(getPosition(letter1), getPosition(letter2));
    }

    public char get(int row, int col) {
        return layout[row][col];
    }

    public void set(int row, int col, char letter) {
        layout[row][col] = letter;
        positions[language.getAlphabet().indexOf(letter)] = new Position(row, col);
    }

    public Position getPosition(char letter) {
        return positions[language.getAlphabet().indexOf(Character.toUpperCase(letter))];
    }

    public Language getLanguage() {
        return language;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(layout);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyboardLayout that = (KeyboardLayout) o;
        return Arrays.deepEquals(layout, that.layout);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();

        for (char[] row : layout) {
            sb.append(Arrays.toString(row)).append('\n');
        }

        return sb.toString();
    }

}
