package piekie.sensors.view.menu;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;

import io.karim.MaterialTabs;
import piekie.sensors.R;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class MenuActivity extends AppCompatActivity {

    private final String TAG = "Menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_menu);

        ViewPager pager = (ViewPager) findViewById(R.id.main_pager);

        pager.setAdapter(new MenuPagerAdapter(getSupportFragmentManager(), 5));

        MaterialTabs tabs = (MaterialTabs) findViewById(R.id.main_tabs);
        tabs.setViewPager(pager);

        tabs.setOnTabSelectedListener(new MaterialTabs.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Log.i(TAG, "onTabSelected called with position " + position);
            }
        });

        tabs.setOnTabReselectedListener(new MaterialTabs.OnTabReselectedListener() {
            @Override
            public void onTabReselected(int position) {
                Log.i(TAG, "onTabReselected called with position " + position);
            }
        });

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);
    }
}
