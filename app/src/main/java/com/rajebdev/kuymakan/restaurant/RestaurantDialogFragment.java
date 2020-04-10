package com.rajebdev.kuymakan.restaurant;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.rajebdev.kuymakan.foodsection.FoodHeaderSection;
import com.rajebdev.kuymakan.foodsection.FoodItemSection;
import com.rajebdev.kuymakan.foodsection.FoodSectionAdapter;
import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.Section;
import com.shuhart.stickyheader.StickyHeaderItemDecorator;

import java.util.ArrayList;
import java.util.Objects;

public class RestaurantDialogFragment extends DialogFragment {

    public static String TAG = TAG = "FullScreenDialog";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.dialog_fragment_restaurant, container, false);

        // Textview Restaurant Name
        final TextView restaurantName = view.findViewById(R.id.restaurant_name);

        // Setup CollapsingToolbarLayout
        final CollapsingToolbarLayout  collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);

        // Setup btnMaps
        final View btnRestaurantMaps = view.findViewById(R.id.btn_restaurant_maps);
        btnRestaurantMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                new RestaurantMapsDialogFragment().show(getFragmentManager().beginTransaction(), RestaurantMapsDialogFragment.TAG);
            }
        });

        // Setup AppBar
        AppBarLayout appBarLayout = view.findViewById(R.id.appbar_restaurant);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                collapsingToolbarLayout.setContentScrimResource(R.color.colorDarkPart);
                restaurantName.setVisibility( (verticalOffset != 0) ? ViewGroup.VISIBLE : ViewGroup.GONE);
                btnRestaurantMaps.setVisibility( (verticalOffset == -appBarLayout.getTotalScrollRange()) ? ViewGroup.GONE : ViewGroup.VISIBLE);
            }
        });


        // Tombol Back
        ImageButton btnRestaurantClose = view.findViewById(R.id.btn_restaurant_close);
        btnRestaurantClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // Tombol Search
        ImageButton btnRestaurantFoodSearch = view.findViewById(R.id.btn_restaurant_food_search);
        btnRestaurantFoodSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                new RestaurantFoodSearchDialogFragment().show(getFragmentManager().beginTransaction(), RestaurantFoodSearchDialogFragment.TAG);
            }
        });

        // Tombol Share
        ImageButton btnRestaurantShare = view.findViewById(R.id.btn_restaurant_share);
        btnRestaurantShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareSub = "KuyMakan";
                String shareBody = "Bagikan Restaurant";
                intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "Share With"));
            }
        });

        // Setup Recycle View for Food List
        setUpRecycleViewFoodList(view);

        return view;
    }

    private void setUpRecycleViewFoodList(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_restaurant_food_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        FoodSectionAdapter adapter = new FoodSectionAdapter(this);
        recyclerView.setAdapter(adapter);

        StickyHeaderItemDecorator decorator = new StickyHeaderItemDecorator(adapter);
        decorator.attachToRecyclerView(recyclerView);

        adapter.items = new ArrayList<Section>() {{
            int section = 0;
            add(new RestaurantHeaderSection());
            for (int i = 0; i < 28; i++) {
                if (i < 12) {
                    if (i % 4 == 0) {
                        section = i;
                        add(new FoodHeaderSection(section));
                    } else {
                        add(new FoodItemSection(section));
                    }
                } else {
                    if (i % 8 == 0) {
                        section = i;
                        add(new FoodHeaderSection(section));
                    } else {
                        add(new FoodItemSection(section));
                    }
                }
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