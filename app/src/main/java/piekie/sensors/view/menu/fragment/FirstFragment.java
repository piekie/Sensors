package piekie.sensors.view.menu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import piekie.sensors.R;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class FirstFragment extends Fragment {

    public static FirstFragment newInstance() {
        FirstFragment f = new FirstFragment();
        return f;
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

        return rootView;
    }
}
