package piekie.sensors.view.action;

import android.os.Bundle;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by piekie (Artem Vasylenko)
 * on 10/27/16
 */

public class ActionController {

    public static DiscreteSeekBar.OnProgressChangeListener getOnChangeListener(ActionView actionView,
                                                                               String name, Bundle extras) {
        return new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                actionView.push(name, Integer.toString(value));
                extras.putInt(name, value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        };
    }
}
