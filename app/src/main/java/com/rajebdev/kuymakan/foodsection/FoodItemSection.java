package com.rajebdev.kuymakan.foodsection;

import com.rajebdev.kuymakan.Section;

public class FoodItemSection implements Section {
    private int section;

    public FoodItemSection(int section) {
        this.section = section;
    }

    @Override
    public int type() {
        return ITEM;
    }

    @Override
    public int sectionPosition() {
        return section;
    }
}
