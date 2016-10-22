package piekie.sensors.util;

import android.support.annotation.Nullable;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public final class MathUtils {

    /**
     * Return amount of corners in polygon
     *
     * @param side   - side of polygon
     * @param radius - radius of сircumscribed circle (circumcircle)
     * @return amount of corners.
     */
    public static double getAmountOfCornersInPolygon(double side, double radius) {
        double cosine = (2 * radius * radius - side * side)
                / (2 * radius * radius);

        double angleInDegrees = Math.toDegrees(Math.acos(cosine));

        return 360 / angleInDegrees;
    }

    /**
     * Get angle that lies between radii in circumcircle of polygon
     *
     * @param numberOfCorners - number of corners in polygon
     * @return angle in polygon between radii
     */
    public static double getAngleInPolygon(int numberOfCorners) {
        return (180 * (numberOfCorners - 2)) / (2 * numberOfCorners);
    }

    /**
     * Return angle between two points (Ox)
     *
     * @param a first point
     * @param b second point
     * @return angles
     */
    public static double getAngleBetweenPoints(Point a, Point b) {
        double result = Math.atan2(a.y - b.y, a.x - b.x) / Math.PI * 180;

        return (result < 0) ? result + 360 : result;
    }

    /**
     * @param first  <code>LineSegment</code> instance
     * @param second <code>LineSegment</code> instance
     * @return <code>true</code> if this segments intersects
     */
    @Nullable
    public static Point getIntersection(LineSegment first, LineSegment second) {
        Point intersection = null;

        if (first.isVertical()) {

            if ((second.first.x - first.first.x) * (second.second.x - first.first.x) > 0)
                intersection = null;
            else {
                double fx_at_segment1firstx = first.getSlope() * first.first.x + first.getIntercept();

                double smaller, larger;

                if (first.first.x < second.second.x) {
                    smaller = first.first.x;
                    larger = first.second.x;
                } else {
                    larger = first.first.x;
                    smaller = first.second.x;
                }

                if (smaller <= fx_at_segment1firstx && fx_at_segment1firstx <= larger) {

                    // TODO: 10/22/16 add coordinates here
                    intersection = new Point();

                }
            }

        } else if (second.isVertical()) {
            return getIntersection(second, first);

        } else {
            double x1 = first.first.x;
            double y1 = first.first.y;

            double x2 = first.second.x;
            double y2 = first.second.y;

            double x3 = second.first.x;
            double y3 = second.first.y;

            double x4 = second.second.x;
            double y4 = second.second.y;

            double x = ((x4 * y3 - y4 * x3) / (x4 - x3) - (x2 * y1 - y2 * x1) / (x2 - x1))
                    / ((y2 - y1) / (x2 - x1) - (y4 - y3) / (x4 - x3));

            double smaller1, larger1;
            double smaller2, larger2;

            if (x1 < x2) {
                smaller1 = x1;
                larger1 = x2;
            } else {
                smaller1 = x2;
                larger1 = x1;
            }
            if (x3 < x4) {
                smaller2 = x3;
                larger2 = x4;
            } else {
                smaller2 = x4;
                larger2 = x3;
            }

            if (smaller1 <= x && x <= larger1 && smaller2 <= x && x <= larger2) {

                // TODO: 10/22/16 add coordinates here
                intersection = new Point(x, 0);
            }
        }

        return intersection;
    }

    /**
     * @param rectangle Rectangle to detect intersection
     * @param segment   Segment
     * @return Point of intersection
     */
    @Nullable
    public static Point getIntersection(Rectangle rectangle, LineSegment segment) {
        LineSegment[] segmentsToCheck = rectangle.getAllSides();
        Point intersection = null;

        for (LineSegment s : segmentsToCheck) {
            intersection = getIntersection(segment, s);

            if (intersection != null) {
                break;
            }
        }

        return intersection;
    }

}
