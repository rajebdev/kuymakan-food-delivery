package com.rajebdev.kuymakan.buyer.restaurant;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rajebdev.kuymakan.RestClient;
import com.rajebdev.kuymakan.buyer.food.FoodData;
import com.rajebdev.kuymakan.buyer.foodsection.FoodHeaderSection;
import com.rajebdev.kuymakan.buyer.foodsection.FoodItemSection;
import com.rajebdev.kuymakan.buyer.foodsection.FoodSectionAdapter;
import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.Section;
import com.rajebdev.kuymakan.buyer.foodtype.FoodTypeData;
import com.rajebdev.kuymakan.buyer.foodtype.FoodTypeListAdapter;
import com.rajebdev.kuymakan.buyer.foodtype.FoodTypeRespond;
import com.shuhart.stickyheader.StickyHeaderItemDecorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantDialogFragment extends DialogFragment {

    public static String TAG = TAG = "FullScreenDialog";

    private int restaurantId;

    private RestaurantData restaurantData;
    private FoodSectionAdapter adapter;

    public RestaurantDialogFragment(int restaurant) {
        restaurantId = restaurant;
    }

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
        TextView restaurantName = view.findViewById(R.id.restaurant_name);

        // Setup CollapsingToolbarLayout
        final CollapsingToolbarLayout  collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);

        // Setup btnMaps
        final View btnRestaurantMaps = view.findViewById(R.id.btn_restaurant_maps);
        btnRestaurantMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RestaurantMapsDialogFragment().show(getChildFragmentManager().beginTransaction(), RestaurantMapsDialogFragment.TAG);
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
                new RestaurantFoodSearchDialogFragment().show(getChildFragmentManager().beginTransaction(), RestaurantFoodSearchDialogFragment.TAG);
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

        RestClient.getService(getContext()).getRestaurant(restaurantId).enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                if (response.isSuccessful()){
                    restaurantData =  response.body().getRestaurantData();
                    restaurantName.setText(restaurantData.getNames());
                }
            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal Load "+t.toString(), Toast.LENGTH_LONG).show();
            }
        });

        // Setup Recycle View for Food List
        setUpRecycleViewFoodList(view);

        // Button Checkout

        FloatingActionButton btnCheckout = view.findViewById(R.id.btn_checkout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new OrderListBottomSheetDialogFragment(adapter.orderFoods, restaurantId).show(getChildFragmentManager().beginTransaction(), "Order List");
            }
        });

        return view;
    }

    private void setUpRecycleViewFoodList(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_restaurant_food_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter = new FoodSectionAdapter(this, restaurantId);
        recyclerView.setAdapter(adapter);

        StickyHeaderItemDecorator decorator = new StickyHeaderItemDecorator(adapter);
        decorator.attachToRecyclerView(recyclerView);

        adapter.items = new ArrayList<Section>() {{
            int section = 0;
            add(new RestaurantHeaderSection());
            add(new FoodHeaderSection(section));
            RestClient.getService(getContext()).getRestaurant(restaurantId).enqueue(new Callback<RestaurantResponse>() {
                @Override
                public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                    if (response.isSuccessful()){
                        restaurantData =  response.body().getRestaurantData();
                        Log.e(TAG, "onResponse: "+ restaurantData.getFoods().size());
                        for (int i=0; i < restaurantData.getFoods().size(); i++){
                            FoodItemSection foodItemSection = new FoodItemSection(0, i);
                            add(foodItemSection);
                        }
                    }
                }

                @Override
                public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Gagal Load "+t.toString(), Toast.LENGTH_LONG).show();
                }
            });
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