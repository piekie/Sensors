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

    /**
     * Get the bounding box of this line by two points. The first point is in
     * the lower left corner, the second one at the upper right corner.
     *
     * @return the bounding box
     */
    public Point[] getBoundingBox() {
        Point[] result = new Point[2];
        result[0] = new Point(Math.min(first.x, second.x), Math.min(first.y,
                second.y));
        result[1] = new Point(Math.max(first.x, second.x), Math.max(first.y,
                second.y));
        return result;
    }
}