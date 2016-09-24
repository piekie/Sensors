package piekie.sensors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import piekie.sensors.view.ActionView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ActionView(this));
    }
}
