package domain.seat;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 29.08.2023, Di.
 */
public record Position(int x, int y) {

    public double distance(Position other) {
        return Math.hypot(x - other.x, y - other.y);
    }

}
