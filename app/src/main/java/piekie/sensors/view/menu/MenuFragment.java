package piekie.sensors.view.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import piekie.sensors.R;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class MenuFragment extends Fragment {


    private static final String ARG_POSITION = "position";

    @InjectView(R.id.textView)
    TextView mTextView;

    private int position;

    public static MenuFragment newInstance(int position) {
        MenuFragment f = new MenuFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.inject(this, rootView);
        ViewCompat.setElevation(rootView, 50);
        mTextView.setText("Fragment #" + position);
        return rootView;
    }
}
