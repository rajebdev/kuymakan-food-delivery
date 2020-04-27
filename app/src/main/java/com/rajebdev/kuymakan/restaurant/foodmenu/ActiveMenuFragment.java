package com.rajebdev.kuymakan.restaurant.foodmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;

import java.util.ArrayList;

public class ActiveMenuFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_menu_active, container, false);
        setUpRecycleView(view);
        return view;
    }


    private void setUpRecycleView(View view) {
        // Set Data For Recycle View
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_food_menu_list);
        mRecyclerView.setLayoutManager(layoutManager);

        ArrayList<FoodMenuData> data = new ArrayList<>();
        for (int i = 0; i < 1; i++)
        {
            data.add(new FoodMenuData());
        }
        FoodMenuListAdapter listAdapter = new FoodMenuListAdapter(data, this);
        mRecyclerView.setAdapter(listAdapter);
    }
}