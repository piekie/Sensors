package piekie.sensors.view.menu;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.karim.MaterialTabs;
import piekie.sensors.R;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class MenuActivity extends AppCompatActivity {

    private final String TAG = "Menu";

    @InjectView(R.id.menu_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_menu);

        ButterKnife.inject(this);
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        // Setting this navigation icon's onClickListener
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);

        ViewPager pager = (ViewPager) findViewById(R.id.menu_pager);

        pager.setAdapter(new MenuPagerAdapter(getSupportFragmentManager(), 5));

        MaterialTabs tabs = (MaterialTabs) findViewById(R.id.menu_tabs);
        tabs.setViewPager(pager);

        tabs.setOnTabSelectedListener(position -> Log.i(TAG, "onTabSelected called with position " + position));

        tabs.setOnTabReselectedListener(position -> Log.i(TAG, "onTabReselected called with position " + position));

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }
}
