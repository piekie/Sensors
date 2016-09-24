package piekie.sensors.view.menu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.glomadrian.materialanimatedswitch.MaterialAnimatedSwitch;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import piekie.sensors.R;
import piekie.sensors.domain.Scene;
import piekie.sensors.view.action.ActionView;

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

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        ButterKnife.inject(this, rootView);

        ViewCompat.setElevation(rootView, 50);

        final ActionView action = (ActionView) rootView.findViewById(R.id.menu_action_example);
        action.initialize(null, Scene.TEST_FIRST);


        seekBarSize.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                action.push("size", Integer.toString(value));
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
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        switchRotate.setOnCheckedChangeListener(new MaterialAnimatedSwitch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean b) {
                action.push("isRotating", Boolean.toString(b));
            }
        });

        return rootView;
    }
}
