package domain.keyboard;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 25.08.2023, Fr.
 */
public class KeyboardLayout {

    private final char[][] layout;

    private final KeyPosition[] positions;

    @Getter
    private final Language language;

    public KeyboardLayout(Language language) {
        this.language = language;
        this.positions = new KeyPosition[language.getAlphabet().length()];
        this.layout = new char[language.getRowCount()][];

        for (int j = 0; j < layout.length; j++) {
            layout[j] = new char[language.getColumnCount(j)];
        }
    }

    public void swap(char letter1, char letter2) {
        var pos1 = getLetterPosition(letter1);
        var pos2 = getLetterPosition(letter2);

        char temp = layout[pos1.row()][pos1.col()];
        layout[pos1.row()][pos1.col()] = layout[pos2.row()][pos2.col()];
        layout[pos2.row()][pos2.col()] = temp;

        int letter1Index = language.getLetterIndex(letter1);
        int letter2Index = language.getLetterIndex(letter2);
        var tempPos = positions[letter1Index];
        positions[letter1Index] = positions[letter2Index];
        positions[letter2Index] = tempPos;
    }

    public double totalTravelDistance(char[] chars) {
        double dist = 0;

        for (int i = 0; i < chars.length - 1; i++) {
            dist += distance(chars[i], chars[i + 1]);
        }

        return dist;
    }

    public double distance(char letter1, char letter2) {
        return KeyPosition.distance(getLetterPosition(letter1), getLetterPosition(letter2));
    }

    public char get(int row, int col) {
        return layout[row][col];
    }

    public void set(int row, int col, char letter) {
        layout[row][col] = letter;
        positions[language.getLetterIndex(letter)] = new KeyPosition(row, col);
    }

    public void setAll(char[][] layout) {
        for (int j = 0; j < layout.length; j++) {
            for (int i = 0; i < layout[j].length; i++) {
                set(j, i, layout[j][i]);
            }
        }
    }

    private KeyPosition getLetterPosition(char letter) {
        return positions[language.getLetterIndex(letter)];
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
            for (char c : row) {
                sb.append(c).append(' ');
            }

            sb.append('\n');
        }

        return sb.toString();
    }

}
