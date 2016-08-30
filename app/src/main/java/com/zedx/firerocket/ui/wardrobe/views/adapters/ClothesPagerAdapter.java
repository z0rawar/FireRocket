package com.zedx.firerocket.ui.wardrobe.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by ajmac1005 on 28/08/16.
 */
public class ClothesPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public ClothesPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    void add(Fragment fragment) {
        fragmentList.add(fragment);
        notifyDataSetChanged();

    }
}
