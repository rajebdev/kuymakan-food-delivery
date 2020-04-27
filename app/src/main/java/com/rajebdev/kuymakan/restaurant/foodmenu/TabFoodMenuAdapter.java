package com.rajebdev.kuymakan.restaurant.foodmenu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabFoodMenuAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;

    public TabFoodMenuAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mNumOfTabs = mNumOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ActiveMenuFragment();
            case 1:
                return new UnactiveMenuFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}