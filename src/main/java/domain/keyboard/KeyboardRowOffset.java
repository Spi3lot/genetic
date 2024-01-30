package domain.keyboard;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 25.08.2023, Fr.
 */
public class KeyboardRowOffset {

    private KeyboardRowOffset() {
    }

    public static double shiftColumn(int row, int col) {
        return forRow(row) + col;
    }

    public static double forRow(int row) {
        return 0.25 * ((row + 1) / 2) + 0.5 * (row / 2);
    }

}
