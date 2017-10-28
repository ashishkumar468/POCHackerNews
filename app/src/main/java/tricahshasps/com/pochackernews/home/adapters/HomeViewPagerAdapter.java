package tricahshasps.com.pochackernews.home.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import tricahshasps.com.pochackernews.home.adapters.view.utils.HomeViewTab;

/**
 * Created by Ashish on 28/10/17.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    List<HomeViewTab> homeViewTabs;

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
        homeViewTabs = new ArrayList<>();
    }

    public void setHomeViewTabs(List<HomeViewTab> homeViewTabs) {
        this.homeViewTabs = homeViewTabs;
    }

    @Override
    public Fragment getItem(int position) {
        return homeViewTabs.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return homeViewTabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return homeViewTabs.get(position).getTitle();
    }

}
