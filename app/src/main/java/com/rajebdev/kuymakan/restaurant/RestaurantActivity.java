package com.rajebdev.kuymakan.restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.restaurant.menu.OrderFragment;
import com.rajebdev.kuymakan.restaurant.menu.ProfileFragment;
import com.rajebdev.kuymakan.restaurant.menu.PromoFragment;
import com.rajebdev.kuymakan.restaurant.menu.TransactionFragment;

public class RestaurantActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private int countBack = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        // kita set default nya
        loadFragment(new ProfileFragment());

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
            case R.id.order_menu:
                fragment = new OrderFragment();
                break;
            case R.id.transaction_menu:
                fragment = new TransactionFragment();
                break;
            case R.id.promo_menu:
                fragment = new PromoFragment();
                break;
            case R.id.profile_menu:
                fragment = new ProfileFragment();
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode)
        {
            case KeyEvent.KEYCODE_BACK:
                if (countBack > 0) {
                    this.finishAffinity();
                } else {
                    countBack++;
                    Toast.makeText(getApplicationContext(), "Tekan Sekali lagi untuk Keluar Aplikasi", Toast.LENGTH_SHORT).show();
                }
                break;
            case KeyEvent.ACTION_DOWN:
                break;
            default:
                return false;
        }
        return true;
    }

}
