package com.rajebdev.kuymakan.buyer.foodsection;

import com.rajebdev.kuymakan.Section;
import com.rajebdev.kuymakan.buyer.food.FoodData;

public class FoodItemSection implements Section {
    private int section;
    int foodId;

    public FoodItemSection(int section, int foodId) {
        this.section = section;
        this.foodId = foodId;
    }

    @Override
    public int type() {
        return ITEM;
    }

    @Override
    public int getId() { return foodId;}

    @Override
    public int sectionPosition() {
        return section;
    }
}
