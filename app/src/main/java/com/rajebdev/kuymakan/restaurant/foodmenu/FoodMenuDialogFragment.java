package com.rajebdev.kuymakan.restaurant.foodmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.restaurant.order.TabOrderAdapter;
import com.rajebdev.kuymakan.restaurant.withdraw.WithdrawHistoryData;
import com.rajebdev.kuymakan.restaurant.withdraw.WithdrawHistoryListAdapter;

import java.util.ArrayList;

public class FoodMenuDialogFragment extends DialogFragment {
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
        View view = inflater.inflate(R.layout.dialog_fragment_food_menu, container, false);

        // Tombol Close
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // Btn history
        ImageButton btnHistory = view.findViewById(R.id.btn_history);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "History Food Menu", Toast.LENGTH_SHORT).show();
            }
        });

        // Btn Urutkan
        Button btnSort = view.findViewById(R.id.btn_sort);
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Btn Sort", Toast.LENGTH_SHORT).show();
            }
        });

        // Btn Add
        Button btnAddFoodMenu = view.findViewById(R.id.btn_add_food_menu);
        btnAddFoodMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddFoodMenuDialogFragment().show(getChildFragmentManager().beginTransaction(), AddFoodMenuDialogFragment.TAG);
            }
        });

        // Find ID
        tabLayout = view.findViewById(R.id.food_menu_tab_layout);
        viewPager = view.findViewById(R.id.food_menu_view_pager);

        // Setup Tab Layout
        setupTabOrderLayout();
        return view;
    }

    private void setupTabOrderLayout(){
        tabLayout.addTab(tabLayout.newTab().setText("Aktif"));
        tabLayout.addTab(tabLayout.newTab().setText("Tidak Aktif"));

        TabFoodMenuAdapter tabOrderAdapter = new TabFoodMenuAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabOrderAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }
}
