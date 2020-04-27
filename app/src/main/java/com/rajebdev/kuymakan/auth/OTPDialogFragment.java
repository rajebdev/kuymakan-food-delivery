package com.rajebdev.kuymakan.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.User;
import com.rajebdev.kuymakan.buyer.BuyerActivity;
import com.rajebdev.kuymakan.restaurant.RestaurantActivity;

public class OTPDialogFragment extends DialogFragment {
    public static String TAG = "FullScreenDialog";
    private int userType;

    OTPDialogFragment(int userType) {
        this.userType = userType;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_fragment_otp, container, false);

        // Button Next
        Button btnNext = view.findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userType == User.BUYER){
                    Intent buyerIntent = new Intent(getContext(), BuyerActivity.class);
                    startActivity(buyerIntent);
                }
                else if (userType == User.RESTAURANT) {
                    Intent restaurantIntent = new Intent(getContext(), RestaurantActivity.class);
                    startActivity(restaurantIntent);
                }
                else{
                    Toast.makeText(getContext(), "HACKER HAS DIE HERE", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
