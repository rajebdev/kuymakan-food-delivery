package com.rajebdev.kuymakan.foodsection;

import com.rajebdev.kuymakan.Section;

public class FoodHeaderSection implements Section {

    private int section;

    public FoodHeaderSection(int section) {
        this.section = section;
    }

    @Override
    public int type() {
        return HEADER;
    }

    @Override
    public int sectionPosition() {
        return section;
    }
}
