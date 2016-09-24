package piekie.sensors.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import piekie.sensors.domain.Scene;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class World {

    private Dumbo dumbo;
    private Paint dumboPaint;

    private Scene scene;

    public World(Scene scene) {
        if (scene.equals(Scene.TEST_FIRST)) {
            this.scene = scene;
            this.dumbo = new Dumbo();

            dumboPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            dumboPaint.setColor(dumbo.getColor());
            dumboPaint.setStyle(Paint.Style.FILL);
        }
    }

    public void update() {
        dumbo.update();
    }

    public void updateWithValues(String key, String value) {
        if (key.equals("size")) {
            dumbo.size = Integer.parseInt(value);
        }

        if (key.equals("angle")) {
            dumbo.angle = Integer.parseInt(value);
        }

        if (key.equals("angleInc")) {
            dumbo.moving.phi = Integer.parseInt(value);
        }

        if (key.equals("isRotating")) {
            if (value.equals("true")) {
                dumbo.setRotating(true);
            } else {
                dumbo.setRotating(false);
            }
        }

        if (key.equals("step")) {
            dumbo.moving.step = Integer.parseInt(value);
        }

    }

    public void draw(Canvas canvas) {
        if (scene.equals(Scene.TEST_FIRST)) {

            canvas.drawColor(Color.BLACK);

            Paint lines = new Paint(Paint.ANTI_ALIAS_FLAG);
            lines.setColor(Color.GRAY);
            lines.setStyle(Paint.Style.FILL);

            Paint pointOfView = new Paint(Paint.ANTI_ALIAS_FLAG);
            pointOfView.setColor(Color.RED);
            pointOfView.setStyle(Paint.Style.FILL);

            canvas.drawLine(canvas.getWidth() / 2, 0, canvas.getWidth() / 2, canvas.getHeight(), lines);
            canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, lines);


            canvas.drawLine(canvas.getWidth() / 2 + dumbo.x,
                    canvas.getHeight() / 2 + dumbo.y,
                    (int) (canvas.getWidth() / 2 + dumbo.x + 1000 * Math.cos(Math.toRadians(dumbo.angle))),
                    (int) (canvas.getHeight() / 2 + dumbo.y + 1000 * Math.sin(Math.toRadians(dumbo.angle))), pointOfView);


            Paint circle = new Paint(Paint.ANTI_ALIAS_FLAG);
            circle.setColor(Color.GRAY);
            circle.setStyle(Paint.Style.STROKE);

            canvas.drawCircle(canvas.getWidth() / 2 + dumbo.x,
                    canvas.getHeight() / 2 + dumbo.y,
                    dumbo.moving.step, circle);


            Matrix matrix = new Matrix();

            matrix.setRotate((float) dumbo.angle,
                    canvas.getWidth() / 2 + dumbo.x,
                    canvas.getHeight() / 2 + dumbo.x);

            canvas.setMatrix(matrix);

            canvas.drawRect(canvas.getWidth() / 2 + dumbo.x - dumbo.size / 2,
                    canvas.getHeight() / 2 + dumbo.y - dumbo.size / 2,
                    canvas.getWidth() / 2 + dumbo.x + dumbo.size / 2,
                    canvas.getHeight() / 2 + dumbo.y + dumbo.size / 2,
                    dumboPaint);

        }
    }
}
