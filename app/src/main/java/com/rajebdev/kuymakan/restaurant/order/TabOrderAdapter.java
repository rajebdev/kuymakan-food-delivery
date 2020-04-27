package com.rajebdev.kuymakan.restaurant.order;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabOrderAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;

    public TabOrderAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mNumOfTabs = mNumOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProsesFragment();
            case 1:
                return new FinishFragment();
            case 2:
                return new CancelFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}