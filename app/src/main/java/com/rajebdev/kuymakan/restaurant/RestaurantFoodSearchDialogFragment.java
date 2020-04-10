package com.rajebdev.kuymakan.restaurant;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.foodsection.FoodItemSection;
import com.rajebdev.kuymakan.foodsection.FoodSectionAdapter;
import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.Section;

import java.util.ArrayList;
import java.util.Objects;

public class RestaurantFoodSearchDialogFragment extends DialogFragment {

    static String TAG = "FullScreenDialog";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_fragment_restaurant_food_search, container, false);


        // Set Search View
        final SearchView searchView = view.findViewById(R.id.search_food_menu);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();

        // Line Bottom Search View
        View searchPlate = searchView.findViewById(R.id.search_plate);
        searchPlate.setBackgroundColor(Color.TRANSPARENT);

        // Tombol Back
        View btnBackSearch = searchView.findViewById(R.id.search_mag_icon);
        btnBackSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // Setup RecycleView Food Search List
        setUpRecycleViewFoodSearchList(view);

        return view;
    }

    private void setUpRecycleViewFoodSearchList(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_food_search_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        final FoodSectionAdapter adapter = new FoodSectionAdapter(this);
        recyclerView.setAdapter(adapter);

        adapter.items = new ArrayList<Section>() {{
            int section = 0;
            for (int i = 0; i < 10; i++) {
                add(new FoodItemSection(section));
            }
        }};
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            Objects.requireNonNull(dialog.getWindow()).setLayout(width, height);
        }
    }
}