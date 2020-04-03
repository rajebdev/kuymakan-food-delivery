package com.rajebdev.kuymakan;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class HomeFragment extends Fragment {
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Start Flipper Image
        ViewFlipper v_flipper = view.findViewById(R.id.v_flipper);
        int[] images = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3, R.drawable.slider4, R.drawable.slider5};

        for (int image: images)
        {
            ImageView imageView = new ImageView(this.getContext());
            imageView.setBackgroundResource(image);

            v_flipper.addView(imageView);
            v_flipper.setFlipInterval(7000);
            v_flipper.setAutoStart(true);

            v_flipper.setInAnimation(this.getContext(),android.R.anim.slide_in_left);
            v_flipper.setOutAnimation(this.getContext(),android.R.anim.slide_out_right);
        }
        // End Flipper Image

        // Start Food Slider
        FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        for (int i=0; i < 5; i++)
        {
            Fragment itemFoodSlider = new FoodSliderFragment();
            ft.add(R.id.food_slider_container, itemFoodSlider);
        }
        ft.commit();
        // End Food Slider
        return view;
    }

}
