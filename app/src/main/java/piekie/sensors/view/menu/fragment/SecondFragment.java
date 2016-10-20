package piekie.sensors.view.menu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import piekie.sensors.R;
import piekie.sensors.domain.Scene;
import piekie.sensors.view.action.ActionView;

/**
 * Created by piekie (Artem Vasylenko)
 * on 10/20/16
 */

public class SecondFragment extends Fragment {

    @InjectView(R.id.second_action)
    ActionView actionView;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_second, container, false);

        ButterKnife.inject(this, root);


        Intent i = new Intent();
        i.putExtra("bundle", FirstFragment.getExtras());

        actionView.initialize(i, Scene.SECOND);

        return root;
    }
}
