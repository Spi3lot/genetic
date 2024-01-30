package domain.seat;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 29.08.2023, Di.
 */
public class Team {

    private final Position[] seatPositions;

    public Team(int size) {
        this.seatPositions = new Position[size];
    }

    public double totalCommunicationDistance() {
        double dist = 0;

        for (int i = 0; i < seatPositions.length; i++) {
            for (int j = i + 1; j < seatPositions.length; j++) {
                dist += seatPositions[i].distance(seatPositions[j]);
            }
        }

        return dist;
    }

    public int getTeamSize() {
        return seatPositions.length;
    }

    public Position[] getSeatPositions() {
        return seatPositions;
    }

}
