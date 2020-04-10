package com.rajebdev.kuymakan.restaurant;

import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.Section;

public class RestaurantHeaderSection implements Section {
    @Override
    public int type() {
        return CUSTOM_HEADER;
    }

    @Override
    public int sectionPosition() {
        return RecyclerView.NO_POSITION;
    }
}
