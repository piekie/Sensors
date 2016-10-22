package piekie.sensors.util;

public class Point {

    public double x;
    public double y;

    /**
     * Constructor with default parameters
     */
    public Point() {
        this.x = DEFAULTS.x;
        this.y = DEFAULTS.y;
    }

    /**
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private class DEFAULTS {
        public static final double x = 0;
        public static final double y = 0;
    }
}