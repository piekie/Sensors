package piekie.sensors.view.action;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceHolder;

import piekie.sensors.domain.Scene;
import piekie.sensors.model.World;


/**
 * Created by piekie (Artem Vasylenko)
 * on 9/23/16
 */

public class DrawThread extends Thread {
    private static int MAX_FPS = 30;
    private final SurfaceHolder surfaceHolder;
    private boolean runFlag = false;
    private long prevTime;
    private World world;

    public DrawThread(SurfaceHolder surfaceHolder, Scene scene) {
        this.surfaceHolder = surfaceHolder;

        if (scene.equals(Scene.TEST_FIRST)) {
            world = new World(scene);
        }

        prevTime = System.currentTimeMillis();
    }

    public DrawThread(SurfaceHolder surfaceHolder, Scene scene, Bundle info) {
        this.surfaceHolder = surfaceHolder;

        if (scene.equals(Scene.TEST_FIRST)) {
            world = new World(scene);
        } else if (scene.equals(Scene.FIRST)) {
            world = new World(scene, info);
        } else if (scene.equals(Scene.SECOND)) {
            world = new World(scene, info);
        }

        prevTime = System.currentTimeMillis();
    }

    public void push(String key, String value) {
        world.updateWithValues(key, value);
    }

    public void setRunning(boolean run) {
        runFlag = run;
    }

    @Override
    public void run() {
        Canvas canvas;

        while (runFlag) {


            long now = System.currentTimeMillis();
            long elapsedTime = now - prevTime;

            if (elapsedTime > MAX_FPS) {

                /* Pre-Drawing block */

                world.update();
            }


            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas(null);

                synchronized (surfaceHolder) {
                    /* Drawing block */

                    if (canvas != null) {
                        world.draw(canvas);
                    }
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}