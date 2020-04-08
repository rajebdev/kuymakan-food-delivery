package com.rajebdev.kuymakan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        View btnProfileEdit = view.findViewById(R.id.btn_profile_edit);
        btnProfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Profile Edit", Toast.LENGTH_SHORT).show();
            }
        });

        View btnProfileOrder = view.findViewById(R.id.btn_profile_order);
        btnProfileOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Profile Order", Toast.LENGTH_SHORT).show();
            }
        });

        View btnProfileNewCoupon = view.findViewById(R.id.btn_profile_new_coupon);
        btnProfileNewCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Profile New Coupon", Toast.LENGTH_SHORT).show();
            }
        });

        View btnProfileCoupon = view.findViewById(R.id.btn_profile_coupon);
        btnProfileCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Profile Coupon", Toast.LENGTH_SHORT).show();
            }
        });

        View btnProfileHelp = view.findViewById(R.id.btn_profile_help);
        btnProfileHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Profile Help", Toast.LENGTH_SHORT).show();
            }
        });

        View btnProfilePrivacy = view.findViewById(R.id.btn_profile_privacy);
        btnProfilePrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Profile Privacy", Toast.LENGTH_SHORT).show();
            }
        });

        View btnProfileAboutUs = view.findViewById(R.id.btn_profile_about_us);
        btnProfileAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Profile About us", Toast.LENGTH_SHORT).show();
            }
        });

        View btnSignOut = view.findViewById(R.id.btn_sign_out);
        btnProfileAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Profile Sign Out", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
