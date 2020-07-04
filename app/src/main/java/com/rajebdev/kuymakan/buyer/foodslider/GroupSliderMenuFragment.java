package com.rajebdev.kuymakan.buyer.foodslider;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.RestClient;
import com.rajebdev.kuymakan.buyer.food.FoodData;
import com.rajebdev.kuymakan.buyer.food.FoodResponse;
import com.rajebdev.kuymakan.buyer.foodtype.FoodTypeData;
import com.rajebdev.kuymakan.buyer.foodtype.FoodTypeListAdapter;
import com.rajebdev.kuymakan.buyer.foodtype.FoodTypeRespond;
import com.rajebdev.kuymakan.utils.RandomColor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupSliderMenuFragment extends Fragment {
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_slider_menu, container, false);

        // Set Background
        FrameLayout backgroundContainer = view.findViewWithTag("background_group_slider");
        backgroundContainer.setBackgroundColor(new RandomColor().generateLightColor());

        // Setup Recycle View Slider
        setUpRecycleViewFoodMenu(view);

        return view;
    }


    private void setUpRecycleViewFoodMenu(View view) {
        // Set Data For Recycle View
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_food_menu_slider);
        mRecyclerView.setLayoutManager(layoutManager);

        final Fragment main = this;
        RestClient.getService(getContext()).getListFood().enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                if (response.isSuccessful()){
                    List<FoodData> listItem;
                    listItem = response.body().getData();
                    Log.e("FoodData", "onResponse: "+response.body().toString());
                    FoodMenuListAdapter mListadapter = new FoodMenuListAdapter(listItem, main);
                    mRecyclerView.setAdapter(mListadapter);
                }
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                Toast.makeText(main.getContext(), "Gagal Load "+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
        ArrayList<FoodData> data = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            data.add(new FoodData());
        }
        FoodMenuListAdapter mListadapter = new FoodMenuListAdapter(data, this);
        mRecyclerView.setAdapter(mListadapter);
    }
}
