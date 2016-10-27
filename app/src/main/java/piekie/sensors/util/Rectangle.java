package piekie.sensors.util;

import piekie.sensors.domain.Defaults;

/**
 * Created by piekie (Artem Vasylenko)
 * on 10/22/16
 */

public class Rectangle {

    private Point upperLeft, upperRight, lowerLeft, lowerRight;

    private double width;
    private double height;
    private double angle;

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

        this.width = upperRight.x - upperLeft.x;
        this.height = upperLeft.y - lowerLeft.y;

        this.angle = Defaults.Rectangle.angle;
    }

    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;

        this.width = width;
        this.height = height;

        this.angle = 0;
    }

    public Rectangle(double x, double y, double width, double height) {
        this(new Point(x, y), width, height);
    }

    private void invalidate() {
        this.upperRight = new Point(upperLeft.x + width, upperLeft.y);
        this.lowerRight = new Point(upperRight.x, upperRight.y + height);
        this.lowerLeft = new Point(upperLeft.x, upperLeft.y + height);
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

    public Point getUpperLeft() {
        return upperLeft;
    }

    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    public Point getUpperRight() {
        return upperRight;
    }

    public void setUpperRight(Point upperRight) {
        this.upperRight = upperRight;
    }

    public Point getLowerLeft() {
        return lowerLeft;
    }

    public void setLowerLeft(Point lowerLeft) {
        this.lowerLeft = lowerLeft;
    }

    public Point getLowerRight() {
        return lowerRight;
    }

    public void setLowerRight(Point lowerRight) {
        this.lowerRight = lowerRight;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void updateAngle(double delta) {
        setAngle(this.angle + delta);
    }

    public void updateX(double delta) {
        setX(getX() + delta);
    }

    public void updateY(double delta) {
        setY(getY() + delta);
    }

    public double getX() {
        return upperLeft.x;
    }

    public void setX(double x) {
        setUpperLeft(new Point(x, upperLeft.y));
        invalidate();
    }

    public double getY() {
        return upperLeft.y;
    }

    public void setY(double y) {
        setUpperLeft(new Point(upperLeft.x, y));
        invalidate();
    }

    public void setSize(double size) {
        this.width = size;
        this.height = size;
    }
}