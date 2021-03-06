package com.rajebdev.kuymakan.restaurant.menu;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.restaurant.order.TabOrderAdapter;

public class OrderFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_order, container, false);


        // Button
        ImageButton btnSearchOrder = view.findViewById(R.id.btn_search_order);
        btnSearchOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Search Order Now", Toast.LENGTH_SHORT).show();
            }
        });
        final ImageButton btnRingtoneOrder = view.findViewById(R.id.btn_ringtone_order);
        btnRingtoneOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Volume Order Now", Toast.LENGTH_SHORT).show();
            }
        });

        // Find ID
        tabLayout = view.findViewById(R.id.order_tab_layout);
        viewPager = view.findViewById(R.id.order_view_pager);

        // Setup Tab Layout
        setupTabOrderLayout();
        return view;
    }

    private void setupTabOrderLayout(){
        tabLayout.addTab(tabLayout.newTab().setText("Dalam Proses"));
        tabLayout.addTab(tabLayout.newTab().setText("Selesai"));
        tabLayout.addTab(tabLayout.newTab().setText("Batal"));

        TabOrderAdapter tabOrderAdapter = new TabOrderAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabOrderAdapter);
        viewPager.setCurrentItem(0);

        // untuk listener ketik
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