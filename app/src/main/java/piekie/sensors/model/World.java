package piekie.sensors.model;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import piekie.sensors.App;
import piekie.sensors.R;
import piekie.sensors.domain.Scene;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class World {

    public int status;
    private Dumbo dumbo;
    private Dumbo block;
    private Paint dumboPaint;
    private Paint blockPaint;
    private Scene scene;

    public World(Scene scene) {
        this.scene = scene;
        this.dumbo = new Dumbo();

        if (scene.equals(Scene.TEST_FIRST)) {

            dumboPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            dumboPaint.setColor(dumbo.getColor());
            dumboPaint.setStyle(Paint.Style.FILL);
        }
    }

    public World(Scene scene, Bundle info) {
        this.scene = scene;
        this.dumbo = new Dumbo();

        dumboPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dumboPaint.setColor(dumbo.getColor());
        dumboPaint.setStyle(Paint.Style.FILL);


        Resources res = App.getContext().getResources();

        dumbo.x = info.getInt("x", res.getInteger(R.integer.default_dumbo_x));
        dumbo.y = info.getInt("y", res.getInteger(R.integer.default_dumbo_y));

        if (scene.equals(Scene.SECOND)) {
            dumbo.x = -1;
            dumbo.y = -1;

            status = 0;

            block = new Dumbo();
            block.x = -1;
            block.y = -1;

            block.size = res.getInteger(R.integer.default_dumbo_size);

            blockPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            blockPaint.setColor(Color.GREEN);
            blockPaint.setStyle(Paint.Style.FILL);
        }

        dumbo.size = info.getInt("size", res.getInteger(R.integer.default_dumbo_size));
        dumbo.moving.step = info.getInt("step", res.getInteger(R.integer.default_dumbo_step));
        dumbo.moving.phi = info.getInt("angleInc", res.getInteger(R.integer.default_dumbo_angle_increment));
        dumbo.moving.isRotating = info.getBoolean("isRotating", false);

        dumbo.angle = info.getInt("angle", res.getInteger(R.integer.default_dumbo_angle));


        dumbo.brain.circle = info.getBoolean("isCircle", false);
        dumbo.brain.direction = info.getInt("direction", res.getInteger(R.integer.default_dumbo_direction));
        dumbo.brain.way = info.getInt("way", res.getInteger(R.integer.default_dumbo_way));

        dumbo.brain.initialize();
    }

    public void initDumbo(Bundle info) {
        this.dumbo = new Dumbo();

        Resources res = App.getContext().getResources();

        dumbo.x = info.getInt("x", res.getInteger(R.integer.default_dumbo_x));
        dumbo.y = info.getInt("y", res.getInteger(R.integer.default_dumbo_y));

        if (scene.equals(Scene.SECOND)) {
            dumbo.x = -1;
            dumbo.y = -1;

            status = 0;

            block = new Dumbo();
            block.x = -1;
            block.y = -1;

            block.size = res.getInteger(R.integer.default_dumbo_size);

            blockPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            blockPaint.setColor(Color.GREEN);
            blockPaint.setStyle(Paint.Style.FILL);
        }

        dumbo.size = info.getInt("size", res.getInteger(R.integer.default_dumbo_size));
        dumbo.moving.step = info.getInt("step", res.getInteger(R.integer.default_dumbo_step));
        dumbo.moving.phi = info.getInt("angleInc", res.getInteger(R.integer.default_dumbo_angle_increment));
        dumbo.moving.isRotating = info.getBoolean("isRotating", false);

        dumbo.angle = info.getInt("angle", res.getInteger(R.integer.default_dumbo_angle));


        dumbo.brain.circle = info.getBoolean("isCircle", false);
        dumbo.brain.direction = info.getInt("direction", res.getInteger(R.integer.default_dumbo_direction));
        dumbo.brain.way = info.getInt("way", res.getInteger(R.integer.default_dumbo_way));

        dumbo.brain.initialize();

        dumbo.brain.setBlock(block);
    }

    public void update() {
        if (scene.equals(Scene.SECOND)) {
            if (status >= 3) {
                dumbo.update(scene);
            }
        } else {
            dumbo.update(scene);
        }
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

        if (key.equals("way")) {
            dumbo.brain.way = Integer.parseInt(value);
        }

        if (key.equals("direction")) {
            dumbo.brain.direction = Integer.parseInt(value);
        }

        if (key.equals("isCircle")) {
            dumbo.brain.circle = value.equals("true");
        }

        if (key.equals("touch_x")) {
            float x = Float.parseFloat(value);

            if (status == 0) {
                dumbo.x = (int) x;
            } else if (status == 1) {
                block.x = (int) x;
            }
        }

        if (key.equals("touch_y")) {
            float y = Float.parseFloat(value);

            if (status == 0) {
                dumbo.y = (int) y;
            } else if (status == 1) {
                block.y = (int) y;
            }
        }

        if (key.equals("status")) {
            status += 1;
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


            Paint directionCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
            directionCircle.setColor(Color.WHITE);
            directionCircle.setStyle(Paint.Style.FILL);

            canvas.drawCircle(
                    (float) (canvas.getWidth() / 2 + dumbo.x + (dumbo.moving.step * Math.cos(Math.toRadians(dumbo.brain.direction)))),
                    (float) (canvas.getHeight() / 2 + dumbo.y + (dumbo.moving.step * Math.sin(Math.toRadians(dumbo.brain.direction)))), 10, directionCircle);

            canvas.save();


            canvas.rotate((float) dumbo.angle, canvas.getWidth() / 2 + dumbo.x, canvas.getHeight() / 2 + dumbo.y);

            //DUMBO DRAWING

            canvas.drawRect(canvas.getWidth() / 2 + dumbo.x,
                    canvas.getHeight() / 2 + dumbo.y - dumbo.size / 2,
                    canvas.getWidth() / 2 + dumbo.x + dumbo.moving.step,
                    canvas.getHeight() / 2 + dumbo.y + dumbo.size / 2,
                    dumboPaint);
            canvas.restore();

        } else if (scene.equals(Scene.FIRST)) {
            canvas.drawColor(Color.BLACK);

            Paint pointOfView = new Paint(Paint.ANTI_ALIAS_FLAG);
            pointOfView.setColor(Color.RED);
            pointOfView.setStyle(Paint.Style.FILL);


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


            canvas.save();

            canvas.rotate((float) dumbo.angle, canvas.getWidth() / 2 + dumbo.x, canvas.getHeight() / 2 + dumbo.y);

            canvas.drawRect(canvas.getWidth() / 2 + dumbo.x,
                    canvas.getHeight() / 2 + dumbo.y - dumbo.size / 2,
                    canvas.getWidth() / 2 + dumbo.x + dumbo.moving.step,
                    canvas.getHeight() / 2 + dumbo.y + dumbo.size / 2,
                    dumboPaint);
            canvas.restore();
        } else if (scene.equals(Scene.SECOND)) {

            canvas.drawColor(Color.BLACK);

            if (dumbo.x != -1 && dumbo.y != -1) {

                Paint pointOfView = new Paint(Paint.ANTI_ALIAS_FLAG);
                pointOfView.setColor(Color.RED);
                pointOfView.setStyle(Paint.Style.FILL);


                canvas.drawLine(dumbo.x,
                        dumbo.y,
                        (float) (dumbo.x + 1000 * Math.cos(Math.toRadians(dumbo.angle))),
                        (float) (dumbo.y + 1000 * Math.sin(Math.toRadians(dumbo.angle))), pointOfView);

                canvas.save();

                canvas.rotate((float) dumbo.angle, dumbo.x, dumbo.y);

                canvas.drawRect(dumbo.x,
                        dumbo.y - dumbo.size / 2,
                        dumbo.x + dumbo.moving.step,
                        dumbo.y + dumbo.size / 2,
                        dumboPaint);
                canvas.restore();
            }

            if (block.x != -1 && block.y != -1) {
                canvas.drawRect(block.x,
                        block.y - block.size / 2,
                        block.x + block.size,
                        block.y + block.size / 2,
                        blockPaint);
            }
        }
    }
}
