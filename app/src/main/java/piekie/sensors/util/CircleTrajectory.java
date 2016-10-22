package piekie.sensors.util;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class CircleTrajectory {

    public double cx;

    public double cy;

    public double radius;

    public double side;

    public int amountOfPoints = -1;

    public double angleInside;

    public CircleTrajectory() {
    }

    public CircleTrajectory(double cx, double cy, double radius) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;

        amountOfPoints = -1;
    }

    public boolean initialized() {
        return amountOfPoints != -1;
    }

    public void initialize() {
        if (amountOfPoints != -1) {
            angleInside = MathUtils.getAngleInPolygon(amountOfPoints);
        }
    }

    public double getAngleInDegree(Point currentPosition) {
        return 180 - angleInside - MathUtils.getAngleBetweenPoints(currentPosition, new Point(cx, cy));
    }

    public void setSide(double side) {
        this.side = side;

        updateAmountOfPoints();
        initialize();
    }

    public int getDeltaX() {

        return 0;
    }

    public int getDeltaY() {

        return 0;
    }


    private void updateAmountOfPoints() {
        amountOfPoints = (int) MathUtils.getAmountOfCornersInPolygon(side, radius);
    }
}
