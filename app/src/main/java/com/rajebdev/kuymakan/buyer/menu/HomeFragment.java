package com.rajebdev.kuymakan.buyer.menu;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.RestClient;
import com.rajebdev.kuymakan.buyer.favoritefood.FavoriteFoodListDialogFragment;
import com.rajebdev.kuymakan.buyer.featured.FeaturedData;
import com.rajebdev.kuymakan.buyer.featured.FeaturedListAdapter;
import com.rajebdev.kuymakan.buyer.foodcategory.CategoryFragment;
import com.rajebdev.kuymakan.buyer.foodcategory.FoodCategoryData;
import com.rajebdev.kuymakan.buyer.foodcategory.FoodCategoryRespond;
import com.rajebdev.kuymakan.buyer.foodslider.GroupSliderMenuFragment;
import com.rajebdev.kuymakan.buyer.foodtype.FoodTypeData;
import com.rajebdev.kuymakan.buyer.foodtype.FoodTypeRespond;
import com.rajebdev.kuymakan.buyer.foodtype.FoodTypeListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private BottomSheetDialog sheetDialog;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_buyer_home, container, false);

        // Set Search View
        final SearchView searchView = view.findViewById(R.id.search_food);

        // Line Bottom Search View
        View searchPlate = searchView.findViewById(R.id.search_plate);
        searchPlate.setBackgroundColor(Color.TRANSPARENT);

        // Set Flipper Image Slider
        setFlipperImageSlider(view);

        // Setup Recycle view Food Type
        setUpRecycleViewFoodType(view);

        // Start Fragment Transaction
        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();

        // Load All Fragment Child Home
        loadAllFragment(ft);

        // Commit Fragment Transaction
        ft.commit();


        // Setup Recycle view Food Type
        setUpRecycleViewFeatured(view);

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
                new FavoriteFoodListDialogFragment().show(getChildFragmentManager().beginTransaction(), FavoriteFoodListDialogFragment.TAG);
            }
        });

        return view;
    }
    private void showSheetLocation(View parrentView) {
        View containerSheet = parrentView.findViewById(R.id.delivery_location_sheet);
        BottomSheetBehavior sheetBehavior = BottomSheetBehavior.from(containerSheet);
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.bottomsheet_delivery_location, null);

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

    private void setUpRecycleViewFoodType(View view) {
        // Set Data For Recycle View
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        final RecyclerView mRecyclerView = view.findViewById(R.id.rv_food_type_slider);
        mRecyclerView.setLayoutManager(layoutManager);
        final Fragment main = this;
        RestClient.getService(getContext()).getListFoodType().enqueue(new Callback<FoodTypeRespond>() {
            @Override
            public void onResponse(Call<FoodTypeRespond> call, Response<FoodTypeRespond> response) {
                if (response.isSuccessful()){
                    List<FoodTypeData> listItem;
                    listItem = response.body().getData();
                    Log.e("FoodType", "onResponse: "+response.body().toString());
                    FoodTypeListAdapter mListadapter = new FoodTypeListAdapter(listItem, main);
                    mRecyclerView.setAdapter(mListadapter);
                }
            }

            @Override
            public void onFailure(Call<FoodTypeRespond> call, Throwable t) {
                Toast.makeText(main.getContext(), "Gagal Load "+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setUpRecycleViewFeatured(View view) {
        // Set Data For Recycle View
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_featured_slider);
        mRecyclerView.setLayoutManager(layoutManager);

        ArrayList<FeaturedData> data = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            data.add(new FeaturedData());
        }
        FeaturedListAdapter mListadapter = new FeaturedListAdapter(data, this);
        mRecyclerView.setAdapter(mListadapter);
    }


    private void loadAllFragment(final FragmentTransaction ft){

        final Fragment main = this;
        // Start Group Menu Slider
        RestClient.getService(getContext()).getListFoodCategory().enqueue(new Callback<FoodCategoryRespond>() {
            @Override
            public void onResponse(Call<FoodCategoryRespond> call, Response<FoodCategoryRespond> response) {
                if (response.isSuccessful()){
//                    List<FoodCategoryData> listItem;
//                    listItem = response.body().getData();
//                    for (int i=0; i < 9; i++)
//                    {
//                        Fragment itemCategoryGrid = new CategoryFragment(listItem.get(i), main);
//                        ft.add(R.id.grid_category_container, itemCategoryGrid);
//                    }
                }
            }
            @Override
            public void onFailure(Call<FoodCategoryRespond> call, Throwable t) {
                Toast.makeText(main.getContext(), "Gagal Load "+t.toString(), Toast.LENGTH_LONG).show();
            }
        });

        // End Group Menu Slider

        // Start Group Menu Slider
        for (int i=0; i < 1; i++)
        {
            Fragment itemGroupMenuSlider = new GroupSliderMenuFragment();
            ft.add(R.id.group_slider, itemGroupMenuSlider);
        }
        // End Group Menu Slider
    }

}
