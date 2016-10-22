package piekie.sensors.util;

/**
 * Created by piekie (Artem Vasylenko)
 * on 10/22/16
 */

public class Rectangle {

    private Point upperLeft, upperRight, lowerLeft, lowerRight;

    /**
     * @param upperLeft  Upper-left corner
     * @param upperRight Upper-right corner
     * @param lowerLeft  Lower-left corner
     * @param lowerRight Lower-right corner
     */
    public Rectangle(Point upperLeft, Point upperRight, Point lowerLeft, Point lowerRight) {
        this.upperLeft = upperLeft;
        this.upperRight = upperRight;
        this.lowerLeft = lowerLeft;
        this.lowerRight = lowerRight;
    }

    public LineSegment[] getAllSides() {
        return new LineSegment[]{getTopSide(), getRightSide(), getBottomSide(), getLeftSide()};
    }

    public LineSegment getTopSide() {
        return new LineSegment(upperLeft, upperRight);
    }

    public LineSegment getBottomSide() {
        return new LineSegment(lowerLeft, lowerRight);
    }

    public LineSegment getLeftSide() {
        return new LineSegment(upperLeft, lowerLeft);
    }

    public LineSegment getRightSide() {
        return new LineSegment(upperRight, lowerRight);
    }
}