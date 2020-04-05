package com.rajebdev.kuymakan;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class FeaturedSliderFragment extends Fragment {
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_featured_slider, container, false);
        GradientDrawable drawable = (GradientDrawable) view.findViewWithTag("background_featured").getBackground();
        drawable.setColor(new RandomColor().generateDarkColor());
        return view;
    }
}
