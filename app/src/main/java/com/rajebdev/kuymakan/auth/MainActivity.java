package com.rajebdev.kuymakan.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button Buyer
        Button btnBuyer = findViewById(R.id.btn_buyer);
        btnBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginDialogFragment(User.BUYER).show(getSupportFragmentManager(), LoginDialogFragment.TAG);
            }
        });

        // Button Buyer
        Button btnRestaurant = findViewById(R.id.btn_restaurant);
        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginDialogFragment(User.RESTAURANT).show(getSupportFragmentManager(), LoginDialogFragment.TAG);
            }
        });

        // Btn Call Us
        View btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RegisterDialogFragment().show(getSupportFragmentManager(), RegisterDialogFragment.TAG);
            }
        });
    }
}
