package com.rajebdev.kuymakan.buyer.foodcategory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.rajebdev.kuymakan.R;

import java.util.Objects;

public class CategoryFragment extends Fragment {

    Fragment parrent;
    private FoodCategoryData foodCategoryData;
    public CategoryFragment(FoodCategoryData foodCategoryData, Fragment main) {
        foodCategoryData = foodCategoryData;
        parrent = main;
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ImageView imageView = view.findViewWithTag("img_category");
        TextView textView = view.findViewWithTag("text_category");
        textView.setText(foodCategoryData.getLabel());
        Toast.makeText(parrent.getContext(), foodCategoryData.getImages(), Toast.LENGTH_LONG).show();
        Glide.with(parrent.getContext()).load("http://192.168.0.103/apikuymakan/"+foodCategoryData.getImages()).into(imageView);
        return view;
    }
}
