package piekie.sensors.model;

import piekie.sensors.model.scene.Scene;
import piekie.sensors.model.scene.SceneFactory;

/**
 * Created by piekie (Artem Vasylenko)
 * on 10/27/16
 */

public class World {

    private int scenesAmount = 3;
    private Scene[] scenes = new Scene[scenesAmount];

    public World() {

        for (Scene s : scenes) {
            s = SceneFactory.newInsance();
        }
    }

    public void draw() {

    }
}
