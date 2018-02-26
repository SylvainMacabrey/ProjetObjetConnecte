/**
 * Created by sylva on 26/02/2018.
 */
public class Segment {

    private Position p1;
    private Position p2;

    public Segment(Position p1, Position p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public double calculLongueurSegment() {
        double v = Math.pow(p2.getLatitude() - p1.getLatitude(), 2) + Math.pow(p2.getLongitude() - p1.getLongitude(), 2);
        return Math.sqrt(v);
    }
}
