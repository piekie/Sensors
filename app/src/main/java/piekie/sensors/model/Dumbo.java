package piekie.sensors.model;

import android.graphics.Color;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class Dumbo {

    int size;  //ширина = высота

    double angle;  // куда Dumbo смотрит (повернут)

    int x;
    int y;

    private Brain brain;
    private Moving moving;

    public Dumbo() {
        brain = new Brain(this);
        moving = new Moving(this);

        size = 100;
    }

    public void update() {
        brain.think();
    }

    public int getColor() {
        return Color.YELLOW;
    }

    public double getRotate() {
        return angle;
    }
}
