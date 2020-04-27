package com.rajebdev.kuymakan.restaurant.stock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.restaurant.foodmenu.FoodMenuData;
import com.rajebdev.kuymakan.restaurant.foodmenu.FoodMenuListAdapter;

import java.util.ArrayList;

public class StockDialogFragment  extends DialogFragment {
    public static String TAG = "FullScreenDialog";
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_fragment_stock, container, false);


        // Tombol Close
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setUpRecycleView(view);

        // btn list out of stock
        View btnListOutOfStock = view.findViewById(R.id.btn_list_out_of_stock);
        btnListOutOfStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new OutOfStockDialogFragment().show(getChildFragmentManager(), OutOfStockDialogFragment.TAG);
            }
        });
        return view;
    }

    private void setUpRecycleView(View view) {
        // Set Data For Recycle View
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_food_menu_list);
        mRecyclerView.setLayoutManager(layoutManager);

        ArrayList<FoodMenuData> data = new ArrayList<>();
        for (int i = 0; i < 3; i++)
        {
            data.add(new FoodMenuData());
        }
        FoodMenuListAdapter listAdapter = new FoodMenuListAdapter(data, this);
        mRecyclerView.setAdapter(listAdapter);
    }
}