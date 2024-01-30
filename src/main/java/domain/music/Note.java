package domain.music;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Emilio Zottel (4AHIF)
 * @since 29.08.2023, Di.
 */

@Getter
@Setter
@ToString
public class Note {

    private double frequency;
    private double amplitude;
    private double startTime;
    private double duration;

    @ToString.Exclude
    private double endTime;

    public Note(double frequency, double amplitude, double startTime, double duration) {
        this.frequency = frequency;
        this.amplitude = amplitude;
        this.startTime = startTime;
        setDuration(duration);
    }

    public void setDuration(double duration) {
        this.duration = duration;
        this.endTime = this.startTime + this.duration;
    }

}
