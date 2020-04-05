package com.rajebdev.kuymakan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FoodMenuSliderFragment extends Fragment {
    private String dataKirim;
    FoodMenuSliderFragment(String s) {
        dataKirim = s;
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_menu_slider, container, false);

        // Button Add To Cart Setting
        Button btnAddToCart = view.findViewWithTag("btn_add_to_cart");
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Add to Cart "+ dataKirim, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
