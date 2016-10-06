package piekie.sensors.model;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class Brain {

    private final int updatingRatio = 1000;
    public int way = 0;
    public int direction = 0;
    public boolean circle = false;
    private long lastUpdateTimestamp = 0;
    private Dumbo instance;

    public Brain(Dumbo instance) {
        this.instance = instance;
    }

    /**
     * Check if update is allowed by "time logic"
     * (Dumbo can not update himself as fast as possible)
     *
     * @return true if update is allowed.
     */
    private boolean isUpdateAllowed() {
        long now = System.currentTimeMillis();

        if (now - lastUpdateTimestamp >= updatingRatio) {
            lastUpdateTimestamp = now;
            return true;
        } else return false;
    }

    void think() {
        if (isUpdateAllowed()) {
            instance.moving.rotate(Moving.Direction.DEFAULT);
        }
    }
}
