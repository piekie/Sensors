package piekie.sensors.util;

public class LineSegment {
    private final double slope, intercept;
    private final boolean isVertical;
    public Point first;
    public Point second;
    /**
     * @param first  the first point of this line
     * @param second the second point of this line
     */
    public LineSegment(Point first, Point second) {
        this.first = first;
        this.second = second;

        isVertical = first.x == second.x;

        if (!isVertical) {
            slope = (first.y - second.y) / (first.x - second.x);
            intercept = (second.x * first.y - first.x * second.y) / (first.x - second.x);
        } else {
            slope = Double.MAX_VALUE;
            intercept = -Double.MAX_VALUE;
        }
    }

    /**
     * @return <code>true</code> if segment is vertical
     */
    boolean isVertical() {
        return isVertical;
    }

    /**
     * @return slope value for segment
     */
    double getSlope() {
        return slope;
    }

    double getIntercept() {
        return intercept;
    }
}