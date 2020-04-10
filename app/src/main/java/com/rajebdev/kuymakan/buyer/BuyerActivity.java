package com.rajebdev.kuymakan.buyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.coupon.CouponFragment;
import com.rajebdev.kuymakan.info.InfoFragment;
import com.rajebdev.kuymakan.order.OrderFragment;

public class BuyerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);

        // kita set default nya Home Fragment
        loadFragment(new HomeFragment());

        // initialisation BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Tambahkan listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.coordinatorContent, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.home_menu:
                fragment = new HomeFragment();
                break;
            case R.id.order_menu:
                fragment = new OrderFragment();
                break;
            case R.id.coupon_menu:
                fragment = new CouponFragment();
                break;
            case R.id.info_menu:
                fragment = new InfoFragment();
                break;
            case R.id.profile_menu:
                fragment = new ProfileFragment();
                break;
        }
        return loadFragment(fragment);
    }
}
