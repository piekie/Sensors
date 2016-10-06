package piekie.sensors.view.action;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import piekie.sensors.domain.Scene;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new ActionView(this, getIntent(), Scene.FIRST));
    }
}
