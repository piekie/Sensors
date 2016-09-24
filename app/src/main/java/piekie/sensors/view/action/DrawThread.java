package piekie.sensors.view.action;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;

import piekie.sensors.model.Dumbo;
import piekie.sensors.model.World;


/**
 * Created by piekie (Artem Vasylenko)
 * on 9/23/16
 */

public class DrawThread extends Thread {
    private final SurfaceHolder surfaceHolder;
    private boolean runFlag = false;
    private long prevTime;

    private World world;

    public DrawThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;

        world = new World(new Dumbo());

        prevTime = System.currentTimeMillis();
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

            if (elapsedTime > 30) {

                /* Pre-Drawing block */

                world.update();
            }


            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas(null);

                synchronized (surfaceHolder) {
                    /* Drawing block */

                    world.draw(canvas);
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}