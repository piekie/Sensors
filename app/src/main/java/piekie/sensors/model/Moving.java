package piekie.sensors.model;

import piekie.sensors.domain.CircleTrajectory;
import piekie.sensors.domain.Point;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

class Moving {

    private int step;  //шаг движения
    private int phi;    // насколько Dumbo вращается. "дискрета"
    private boolean isRotating;   //вращается ли Дамбо
    private Way movingWay;
    private CircleTrajectory circleTrajectory;
    private Dumbo instance;
    Moving(Dumbo instance) {
        this.instance = instance;
    }

    /**
     * Rotate Dumbo on certain angle (initializing value).
     */
    private void rotate(Direction direction) {
        int delta = phi;

        if (direction.equals(Direction.LEFT)) {
            delta *= -1;
        }

        if (isRotating) {
            instance.angle += delta;
        }
    }

    /**
     * Moves Dumbo to certain point on a circle.
     * Radius of circle is a "step" field.
     * If way of moving is Way.CIRCLE, angle can be any number.
     *
     * @param angleInRadians target angle in radians.
     */
    public void move(double angleInRadians) {
        if (movingWay.equals(Way.CIRCLE) && circleTrajectory.initialized()) {
            Point currentPosition = new Point(instance.x, instance.y);

            instance.x += step * Math.cos(circleTrajectory.getAngleInDegree(currentPosition));
            instance.y += step * Math.sin(circleTrajectory.getAngleInDegree(currentPosition));
        } else {
            instance.x += step * Math.cos(angleInRadians);
            instance.y += step * Math.sin(angleInRadians);
        }
    }

    /**
     * Moves Dumbo to certain point on a circle.
     * Radius of circle is a "step" field.
     * If way of moving is Way.CIRCLE, angle can be any number.
     *
     * @param angle target angle in degrees.
     */
    public void move(int angle) {
        move(Math.toRadians(angle));
    }

    /**
     * Change moving way of Dumbo. If Way.CIRCLE chosen, circleTrajectory must be set previously.
     *
     * @param way Moving way (circle or line)
     */
    public void changeMovingWay(Way way) {
        if (way.equals(Way.CIRCLE)) {
            if (circleTrajectory == null) {
                return;
            }
        }

        this.movingWay = way;
    }

    public void setCircleTrajectory(CircleTrajectory trajectory) {
        circleTrajectory = trajectory;

        circleTrajectory.setSide(step);
    }

    public enum Way {
        LINE, CIRCLE
    }

    public enum Direction {
        RIGHT, LEFT, DEFAULT
    }
}

