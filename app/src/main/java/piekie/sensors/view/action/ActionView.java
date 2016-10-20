package piekie.sensors.view.action;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import piekie.sensors.domain.Scene;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/23/16
 */

public class ActionView extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread drawThread;
    private Intent intent;

    private Scene scene;

    public ActionView(Context context, AttributeSet attrs) {
        super(context, attrs);

        getHolder().addCallback(this);
    }

    public ActionView(Context context, Intent intent, Scene scene) {
        super(context);

        this.intent = intent;
        this.scene = scene;

        getHolder().addCallback(this);

        if (scene.equals(Scene.SECOND)) {
            this.setDrawingCacheEnabled(true);
        }
    }

    public ActionView(Context context) {
        super(context);

        getHolder().addCallback(this);
    }

    public void initialize(Intent intent, Scene scene) {
        this.intent = intent;
        this.scene = scene;
    }

    public void startDrawThread(Scene scene) {
        if (scene.equals(Scene.FIRST)) {

            drawThread = new DrawThread(getHolder(), scene, intent.getBundleExtra("bundle"));
            drawThread.setRunning(true);
            drawThread.start();
        } else if (scene.equals(Scene.SECOND)) {
            drawThread = new DrawThread(getHolder(), scene, intent.getBundleExtra("bundle"));
            drawThread.setRunning(true);
            drawThread.start();
        } else {
            drawThread = new DrawThread(getHolder(), scene);
            drawThread.setRunning(true);
            drawThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startDrawThread(scene);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        drawThread.setRunning(false);

        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void push(String key, String value) {
        if (drawThread != null) {
            drawThread.push(key, value);
        }
    }

    public int getStatus() {
        return drawThread.getStatus();
    }

    public void initDumbo(Bundle b) {
        drawThread.initDumbo(b);
    }
}