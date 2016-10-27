package piekie.sensors.model;

import android.graphics.Bitmap;
import android.util.Log;

import piekie.sensors.util.CircleTrajectory;
import piekie.sensors.util.LineSegment;
import piekie.sensors.util.MathUtils;
import piekie.sensors.util.Point;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class Brain {

    private final int updatingRatio = 1000;
    public int way = 0;
    public int direction = 0;
    public boolean circle = false;
    private long lastUpdateTimestamp = 0;
    private Dumbo instance;
    private Dumbo block;
    private Bitmap map;

    public Brain(Dumbo instance) {
        this.instance = instance;
    }

    public void setBlock(Dumbo block) {
        this.block = block;
    }

    /**
     * Check if update is allowed by "time logic"
     * (Dumbo can not update himself as fast as possible)
     *
     * @return true if update is allowed.
     */
    private boolean isUpdateAllowed() {
        long now = System.currentTimeMillis();

        if (now - lastUpdateTimestamp >= updatingRatio) {
            lastUpdateTimestamp = now;
            return true;
        } else return false;
    }

    void think(Scene scene) {
        if (isUpdateAllowed()) {
            instance.moving.rotate(Moving.Direction.DEFAULT);

            if (scene.equals(Scene.FIRST)) {
                instance.moving.move(direction);

            } else if (scene.equals(Scene.SECOND)) {
                if (block != null && instance.moving.isRotating) {

                    // TODO: 10/22/16 make it more generic

                    LineSegment viewRay = new LineSegment(new Point(instance.getX(), instance.getY()),
                            new Point(instance.getX() + 1000 * Math.cos(Math.toRadians(instance.getAngle())),
                                    instance.getY() + 1000 * Math.sin(Math.toRadians(instance.getAngle()))));


                    Point intersection = MathUtils.getIntersection(block, viewRay);


                    // TODO: 10/22/16 present coordinates of intersection not as a toast

                    if (intersection != null) {
                        Log.e("Intersection", intersection.x + " " + intersection.y);

                        instance.setRotating(false);
                    }
                }
            }
        }
    }

    void initialize() {
        if (circle) {
            CircleTrajectory ct = new CircleTrajectory(
                    instance.getX() + way * Math.cos(Math.toRadians(direction)),
                    instance.getY() + way * Math.sin(Math.toRadians(direction)),
                    way);

            instance.moving.setCircleTrajectory(ct);

            instance.moving.changeMovingWay(Moving.Way.CIRCLE);
        }
    }
}
