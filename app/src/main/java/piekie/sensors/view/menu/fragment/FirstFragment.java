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
import piekie.sensors.domain.Scene;
import piekie.sensors.view.action.ActionView;
import piekie.sensors.view.action.MainActivity;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class FirstFragment extends Fragment {

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
    private Bundle extras;

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
        action.initialize(null, Scene.TEST_FIRST);


        seekBarSize.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                action.push("size", Integer.toString(value));

                //// TODO: 10/4/16 move next line to "button" block

                extras.putInt("size", value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
            }
        });

        seekBarAngle.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                action.push("angle", Integer.toString(value));

                extras.putInt("angle", value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seekBarAngleInc.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                action.push("angleInc", Integer.toString(value));

                extras.putInt("angleInc", value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seekBarStep.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                action.push("step", Integer.toString(value));

                extras.putInt("step", value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seekBarX.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                extras.putInt("x", value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seekBarY.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                extras.putInt("y", value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seekBarWay.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                extras.putInt("way", value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seekBarDirection.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                action.push("direction", Integer.toString(value));
                extras.putInt("direction", value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

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
