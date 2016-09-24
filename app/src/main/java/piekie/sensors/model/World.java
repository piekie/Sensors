package piekie.sensors.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class World {

    private Dumbo dumbo;
    private Paint dumboPaint;

    public World(Dumbo dumbo) {
        this.dumbo = dumbo;

        dumboPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dumboPaint.setColor(dumbo.getColor());
        dumboPaint.setStyle(Paint.Style.FILL);
    }

    public void update() {
        dumbo.update();
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        Paint lines = new Paint(Paint.ANTI_ALIAS_FLAG);
        lines.setColor(Color.WHITE);
        lines.setStyle(Paint.Style.FILL);

        canvas.drawLine(canvas.getWidth() / 2, 0, canvas.getWidth() / 2, canvas.getHeight(), lines);
        canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, lines);


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
