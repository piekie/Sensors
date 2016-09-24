package piekie.sensors.model;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class Dumbo {

    int size;  //ширина = высота

    int angle;  // куда Dumbo смотрит (повернут)

    int x;
    int y;

    private Brain brain;
    private Moving moving;

    public Dumbo() {
        brain = new Brain(this);
        moving = new Moving(this);
    }

    public void update() {
        brain.think();
    }
}
