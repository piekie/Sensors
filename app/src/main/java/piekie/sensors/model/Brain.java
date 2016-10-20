package piekie.sensors.model;

import android.graphics.Bitmap;
import android.util.Log;

import piekie.sensors.domain.CircleTrajectory;
import piekie.sensors.domain.Point;
import piekie.sensors.domain.Scene;
import piekie.sensors.util.Geometry;
import piekie.sensors.util.LineSegment;

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
                if (block != null) {

                    LineSegment base = new LineSegment(new Point(instance.x, instance.y),
                            new Point(instance.x + 1000 * Math.cos(Math.toRadians(instance.angle)),
                                    instance.y + 1000 * Math.sin(Math.toRadians(instance.angle))));

                    Point a = new Point(block.x, block.y);
                    Point b = new Point(block.x + block.size, block.y);
                    Point c = new Point(block.x + block.size, block.y + block.size);
                    Point d = new Point(block.x, block.y + block.size);

                    LineSegment ab = new LineSegment(a, b);
                    LineSegment bc = new LineSegment(b, c);
                    LineSegment cd = new LineSegment(c, d);
                    LineSegment da = new LineSegment(d, a);

                    if (Geometry.doLinesIntersect(base, ab)) {
                        Log.e("GEOMETRY", "TOP");
                        instance.setRotating(false);
                    } else if (Geometry.doLinesIntersect(base, bc)) {
                        Log.e("GEOMETRY", "RIGHT");
                        instance.setRotating(false);
                    } else if (Geometry.doLinesIntersect(base, cd)) {
                        Log.e("GEOMETRY", "BOTTOM");
                        instance.setRotating(false);
                    } else if (Geometry.doLinesIntersect(base, da)) {
                        Log.e("GEOMETRY", "LEFT");
                        instance.setRotating(false);
                    }

                }
            }

        }
    }

    void initialize() {
        if (circle) {
            CircleTrajectory ct = new CircleTrajectory(
                    instance.x + way * Math.cos(Math.toRadians(direction)),
                    instance.y + way * Math.sin(Math.toRadians(direction)),
                    way);

            instance.moving.setCircleTrajectory(ct);

            instance.moving.changeMovingWay(Moving.Way.CIRCLE);
        }
    }
}
