package domain.keyboard;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 25.08.2023, Fr.
 */
public record KeyPosition(int row, int col) {

    public static double distance(KeyPosition a, KeyPosition b) {
        return Math.hypot(
                a.row - b.row,
                KeyboardRowOffset.shiftColumn(a.row, a.col) - KeyboardRowOffset.shiftColumn(b.row, b.col)
        );
    }

}
