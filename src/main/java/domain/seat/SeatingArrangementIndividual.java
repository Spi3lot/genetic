package domain.seat;

import domain.genetic.Individual;
import domain.random.Randomizer;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 29.08.2023, Di.
 */
public class SeatingArrangementIndividual extends Individual<SeatingArrangementIndividual> {

    private final int[][] seats;
    private final int[] teamSizes;
    private final Team[] teams;

    public SeatingArrangementIndividual(int[][] seats, int[] teamSizes) {
        this.seats = seats;
        this.teamSizes = teamSizes;
        this.teams = new Team[teamSizes.length];

        for (int i = 0; i < teamSizes.length; i++) {
            teams[i] = new Team(teamSizes[i]);
        }
    }

    @Override
    public void init() {
        for (var team : teams) {
            for (int i = 0; i < team.getTeamSize(); i++) {
                team.getSeatPositions()[i] = randomSeat();
            }
        }
    }

    @Override
    public SeatingArrangementIndividual crossover(SeatingArrangementIndividual other) {
        var child = new SeatingArrangementIndividual(seats, teamSizes);

        // todo: a lot

        for (int i = 0; i < teams.length; i++) {
            child.teams[i] = (Randomizer.RANDOM.nextBoolean()) ? teams[i] : other.teams[i];
        }

        return child;
    }

    @Override
    public void mutate(double mutationRate) {
        if (Randomizer.RANDOM.nextDouble() < mutationRate) {
            int teamIndex = Randomizer.RANDOM.nextInt(teams.length);
            int seatIndex = Randomizer.RANDOM.nextInt(teams[teamIndex].getTeamSize());

            teams[teamIndex].getSeatPositions()[seatIndex] = randomSeat();
        }
    }

    @Override
    protected double calcFitness() {
        double sum = 0;

        for (var team : teams) {
            sum += team.totalCommunicationDistance();
        }

        return sum;
    }

    private Position randomSeat() {
        return new Position(Randomizer.RANDOM.nextInt(seats[0].length), Randomizer.RANDOM.nextInt(seats.length));
    }

    public Team[] getTeams() {
        return teams;
    }

}
