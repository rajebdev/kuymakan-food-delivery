package com.rajebdev.kuymakan.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.RestClient;
import com.rajebdev.kuymakan.User;
import com.rajebdev.kuymakan.buyer.BuyerActivity;
import com.rajebdev.kuymakan.restaurant.RestaurantActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPDialogFragment extends DialogFragment {
    public static String TAG = "FullScreenDialog";
    private int userType;
    private String username;

    OTPDialogFragment(int userType, String phonenumber) {
        this.userType = userType;
        username = phonenumber;
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
        final EditText edtPassword = view.findViewById(R.id.code_otp);
        // Button Next
        Button btnNext = view.findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginBody  loginBody =  new LoginBody();
                loginBody.setUsername(username);
                loginBody.setPassword(edtPassword.getText().toString());

                Log.e("Data", loginBody.toString());

                RestClient.getService(getContext()).postLogin(loginBody).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            //Save token here
                            String token = response.body().getToken();
                            SharedPreferences preferences = getActivity().getSharedPreferences("com.rajebdev.kuymakan", Context.MODE_PRIVATE);
                            preferences.edit().putString("tokenLogin",token).apply();
                            if (response.body().getToken() != "0") {
                                Toast.makeText(getContext(), "Success Login", Toast.LENGTH_SHORT).show();
                                if (userType == User.BUYER) {
                                    Intent buyerIntent = new Intent(getContext(), BuyerActivity.class);
                                    startActivity(buyerIntent);
                                } else if (userType == User.RESTAURANT) {
                                    Intent restaurantIntent = new Intent(getContext(), RestaurantActivity.class);
                                    startActivity(restaurantIntent);
                                } else {
                                    Toast.makeText(getContext(), "HACKER HAS DIE HERE", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(getContext(), "Gagal Silahkan masukkan yang benar", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("Data", "Gagal Load "+t.toString());
                    }
                });


            }
        });

        return view;
    }
}
