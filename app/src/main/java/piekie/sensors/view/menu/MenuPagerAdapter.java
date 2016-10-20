package piekie.sensors.view.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import piekie.sensors.view.menu.fragment.FirstFragment;
import piekie.sensors.view.menu.fragment.SecondFragment;

/**
 * Created by piekie (Artem Vasylenko)
 * on 9/24/16
 */

public class MenuPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = {"Item One", "Item Two", "Item Three", "Item Four", "Item Five", "Item Six", "Item Seven", "Item Eight",
            "Item Nine", "Item Ten", "Item Eleven"};

    private final ArrayList<String> mTitles;

    public MenuPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);

        mTitles = new ArrayList<>();

        mTitles.addAll(Arrays.asList(TITLES).subList(0, numberOfTabs));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return FirstFragment.newInstance();
            case 1:
                return SecondFragment.newInstance();
            default:
                return MenuFragment.newInstance(position);
        }

    }

    @Override
    public int getCount() {
        return mTitles.size();
    }
}
