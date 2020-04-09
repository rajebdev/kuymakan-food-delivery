package com.rajebdev.kuymakan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private BottomSheetDialog sheetDialog;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Set Search View
        final SearchView searchView = view.findViewById(R.id.search_food);

        // Line Bottom Search View
        View v = searchView.findViewById(R.id.search_plate);
        v.setBackgroundColor(Color.TRANSPARENT);

        // Set Flipper Image Slider
        setFlipperImageSlider(view);

        // Start Fragment Transaction
        FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();

        // Load All Fragment Child Home
        loadAllFragment(ft);

        // Commit Fragment Transaction
        ft.commit();

        //  Set Delivery sheet
        View btnDeliveryLocation = view.findViewById(R.id.btn_delivery_location);
        btnDeliveryLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSheetLocation(view);
            }
        });

        //  Set Favorite Food Button
        View btnFavoriteFood = view.findViewById(R.id.btn_favorite_food);
        btnFavoriteFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                new FavoriteFoodListDialogFragment().show(getFragmentManager().beginTransaction(), FavoriteFoodListDialogFragment.TAG);
            }
        });

        return view;
    }
    private void showSheetLocation(View parrentView) {
        View containerSheet = parrentView.findViewById(R.id.delivery_location_sheet);
        BottomSheetBehavior sheetBehavior = BottomSheetBehavior.from(containerSheet);
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.sheet_delivery_location, null);

        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        sheetDialog = new BottomSheetDialog(Objects.requireNonNull(parrentView.getContext()));
        sheetDialog.setContentView(view);
        Objects.requireNonNull(sheetDialog.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        sheetDialog.show();
        sheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                sheetDialog = null;
            }
        });
    }


    private void setFlipperImageSlider(View view){
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
    }

    private void loadAllFragment(FragmentTransaction ft){

        // Start Food Slider
        for (int i=0; i < 5; i++)
        {
            Fragment itemFoodSlider = new FoodTypeSliderFragment();
            ft.add(R.id.food_slider_container, itemFoodSlider);
        }
        // End Food Slider

        // Start Group Menu Slider
        for (int i=0; i < 9; i++)
        {
            Fragment itemCategoryGrid = new CategoryFragment();
            ft.add(R.id.grid_category_container, itemCategoryGrid);
        }
        // End Group Menu Slider

        // Start Group Menu Slider
        for (int i=0; i < 5; i++)
        {
            Fragment itemGroupMenuSlider = new GroupSliderMenuFragment(View.generateViewId());
            ft.add(R.id.group_slider, itemGroupMenuSlider);
        }
        // End Group Menu Slider

        // Start Featured Slider
        for (int i=0; i < 5; i++)
        {
            Fragment itemFeaturedSlider = new FeaturedSliderFragment();
            ft.add(R.id.featured_slider_container, itemFeaturedSlider);
        }
        // End Featured Slider
    }

}
