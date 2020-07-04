package com.rajebdev.kuymakan.buyer.restaurant;

import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.Section;

public class RestaurantHeaderSection implements Section {
    @Override
    public int type() {
        return CUSTOM_HEADER;
    }

    @Override
    public int getId(){
        return 0;
    }

    @Override
    public int sectionPosition() {
        return RecyclerView.NO_POSITION;
    }
}
