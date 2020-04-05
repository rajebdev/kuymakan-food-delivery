package com.rajebdev.kuymakan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class GroupSliderMenuFragment extends Fragment {
    private int idGroupSliderContainer;
    GroupSliderMenuFragment(int groupSliderId) {
        idGroupSliderContainer = groupSliderId;
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_slider_menu, container, false);

        // Set Id Generate
        LinearLayout foodContainer = view.findViewWithTag("food_container_tag");
        foodContainer.setId(idGroupSliderContainer);

        // Set Background
        FrameLayout backgroundContainer = view.findViewWithTag("background_group_slider");
        backgroundContainer.setBackgroundColor(new RandomColor().generateLightColor());

        FragmentTransaction ft;

        // Start Group Menu Slider
        ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        for (int i=0; i < 5; i++)
        {
            Fragment itemFoodMenuSlider = new FoodMenuSliderFragment(idGroupSliderContainer+" "+i);
            ft.add(idGroupSliderContainer, itemFoodMenuSlider);
        }
        ft.commit();
        // End Group Menu Slider
        return view;
    }
}
