package piekie.sensors.model;

import piekie.sensors.domain.CircleTrajectory;
import piekie.sensors.domain.Defaults;
import piekie.sensors.util.Point;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

class Moving {

    public int step = 100;  //шаг движения
    public int phi = 1;    // насколько Dumbo вращается. "дискрета"
    public boolean isRotating;   //вращается ли Дамбо
    private Way movingWay = Way.LINE;
    private CircleTrajectory circleTrajectory;
    private Dumbo instance;

    Moving(Dumbo instance) {
        this.instance = instance;

        this.instance.setX(Defaults.Rectangle.x);
        this.instance.setY(Defaults.Rectangle.y);
    }

    /**
     * Rotate Dumbo on certain angle (initializing value).
     */
    public void rotate(Direction direction) {
        int delta = phi;

        if (direction.equals(Direction.LEFT)) {
            delta *= -1;
        }

        if (isRotating) {
            instance.updateAngle(delta);
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
            Point currentPosition = new Point(instance.getX(), instance.getY());

            instance.updateX(step * Math.cos(circleTrajectory.getAngleInDegree(currentPosition)));
            instance.updateY(step * Math.sin(circleTrajectory.getAngleInDegree(currentPosition)));
        } else {
            instance.updateX(step * Math.cos(angleInRadians));
            instance.updateY(step * Math.sin(angleInRadians));
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

