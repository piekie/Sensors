package piekie.sensors.model;

import android.graphics.Color;

import piekie.sensors.domain.Defaults;
import piekie.sensors.util.Rectangle;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class Dumbo extends Rectangle {

    Brain brain;
    Moving moving;

    public Dumbo() {
        super(Defaults.Rectangle.x,
                Defaults.Rectangle.y,
                Defaults.Rectangle.width,
                Defaults.Rectangle.height);


        brain = new Brain(this);
        moving = new Moving(this);
    }

    public void setRotating(boolean isRotating) {
        moving.isRotating = isRotating;
    }

    public void update(Scene scene) {
        brain.think(scene);
    }

    public int getColor() {
        return Color.YELLOW;
    }
}
