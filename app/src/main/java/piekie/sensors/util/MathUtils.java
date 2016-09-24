package piekie.sensors.util;

import piekie.sensors.domain.Point;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public final class MathUtils {

    /**
     * Return amount of corners in polygon
     * @param side - side of polygon
     * @param radius - radius of сircumscribed circle (circumcircle)
     * @return amount of corners.
     */
    public static double getAmountOfCornersInPolygon(double side, double radius) {
        return Math.PI / Math.asin(side / 2 * radius);
    }

    /**
     * Get angle that lies between radii in circumcircle of polygon
     * @param numberOfCorners - number of corners in polygon
     * @return angle in polygon between radii
     */
    public static double getAngleInPolygon(int numberOfCorners) {
        return (180 * (numberOfCorners - 2)) / (2 * numberOfCorners);
    }

    /**
     * Return angle between two points (Ox)
     * @param a first point
     * @param b second point
     * @return angles
     */
    public static double getAngleBetweenPoints(Point a, Point b) {
        double result = Math.atan2(a.y - b.y, a.x - b.x) / Math.PI * 180;

        return (result < 0) ? result + 360 : result;
    }

}
