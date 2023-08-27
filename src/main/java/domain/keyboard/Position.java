package domain.keyboard;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 25.08.2023, Fr.
 */
public record Position(int row, int col) {

    public static double distance(Position a, Position b) {
        return Math.hypot(KeyboardRowOffset.shift(a.row) - KeyboardRowOffset.shift(b.row), a.col - b.col);
    }

}
