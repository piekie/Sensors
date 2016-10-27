package piekie.sensors.view.menu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.glomadrian.materialanimatedswitch.MaterialAnimatedSwitch;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import piekie.sensors.R;
import piekie.sensors.model.scene.SceneIdentifier;
import piekie.sensors.view.action.ActionController;
import piekie.sensors.view.action.ActionView;
import piekie.sensors.view.action.MainActivity;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class FirstFragment extends Fragment {

    static private Bundle extras;
    @InjectView(R.id.menu_seekbar_size)
    DiscreteSeekBar seekBarSize;
    @InjectView(R.id.menu_seekbar_angle)
    DiscreteSeekBar seekBarAngle;
    @InjectView(R.id.menu_switch_rotate)
    MaterialAnimatedSwitch switchRotate;
    @InjectView(R.id.menu_seekbar_angle_increment)
    DiscreteSeekBar seekBarAngleInc;
    @InjectView(R.id.menu_seekbar_step)
    DiscreteSeekBar seekBarStep;
    @InjectView(R.id.menu_button_start)
    Button buttonStart;
    @InjectView(R.id.menu_seekbar_x)
    DiscreteSeekBar seekBarX;
    @InjectView(R.id.menu_seekbar_y)
    DiscreteSeekBar seekBarY;
    @InjectView(R.id.menu_seekbar_way)
    DiscreteSeekBar seekBarWay;
    @InjectView(R.id.menu_seekbar_direction)
    DiscreteSeekBar seekBarDirection;
    @InjectView(R.id.menu_switch_way)
    MaterialAnimatedSwitch switchWay;
    ActionView action;

    public static Bundle getExtras() {
        return extras;
    }

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        extras = new Bundle();

        extras.putInt("size", getContext().getResources().getInteger(R.integer.default_dumbo_size));
        extras.putInt("x", getContext().getResources().getInteger(R.integer.default_dumbo_x));
        extras.putInt("y", getContext().getResources().getInteger(R.integer.default_dumbo_y));
        extras.putInt("angle", getContext().getResources().getInteger(R.integer.default_dumbo_angle));
        extras.putInt("angleInc", getContext().getResources().getInteger(R.integer.default_dumbo_angle_increment));
        extras.putInt("step", getContext().getResources().getInteger(R.integer.default_dumbo_step));
        extras.putInt("direction", getContext().getResources().getInteger(R.integer.default_dumbo_direction));
        extras.putInt("way", getContext().getResources().getInteger(R.integer.default_dumbo_way));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        ButterKnife.inject(this, rootView);

        ViewCompat.setElevation(rootView, 50);

        action = (ActionView) rootView.findViewById(R.id.menu_action_example);
        action.initialize(null, SceneIdentifier.FIRST_TEST);


        seekBarSize.setOnProgressChangeListener(ActionController.getOnChangeListener(action, "size", extras));
        seekBarAngle.setOnProgressChangeListener(ActionController.getOnChangeListener(action, "angle", extras));
        seekBarAngleInc.setOnProgressChangeListener(ActionController.getOnChangeListener(action, "angleInc", extras));
        seekBarStep.setOnProgressChangeListener(ActionController.getOnChangeListener(action, "step", extras));
        seekBarX.setOnProgressChangeListener(ActionController.getOnChangeListener(action, "x", extras));
        seekBarY.setOnProgressChangeListener(ActionController.getOnChangeListener(action, "y", extras));
        seekBarWay.setOnProgressChangeListener(ActionController.getOnChangeListener(action, "way", extras));
        seekBarDirection.setOnProgressChangeListener(ActionController.getOnChangeListener(action, "direction", extras));

        switchRotate.setOnCheckedChangeListener(b -> {
            action.push("isRotating", Boolean.toString(b));
            extras.putBoolean("isRotating", b);
        });

        switchWay.setOnCheckedChangeListener(b -> {
            action.push("isCircle", Boolean.toString(b));
            extras.putBoolean("isCircle", b);
        });


        buttonStart.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), MainActivity.class);

            i.putExtra("bundle", extras);

            startActivity(i);
        });

        return rootView;
    }
}
