package piekie.sensors.model;

import android.graphics.Color;

import piekie.sensors.domain.Scene;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class Dumbo {

    int size;  //ширина = высота

    double angle;  // куда Dumbo смотрит (повернут)

    int x;
    int y;

    Brain brain;
    Moving moving;

    public Dumbo() {
        brain = new Brain(this);
        moving = new Moving(this);

        size = 90;
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

    public double getRotate() {
        return angle;
    }


}
