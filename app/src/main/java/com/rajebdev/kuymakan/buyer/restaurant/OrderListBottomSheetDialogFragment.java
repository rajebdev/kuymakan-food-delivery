package com.rajebdev.kuymakan.buyer.restaurant;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.RestClient;
import com.rajebdev.kuymakan.User;
import com.rajebdev.kuymakan.auth.LoginResponse;
import com.rajebdev.kuymakan.buyer.BuyerActivity;
import com.rajebdev.kuymakan.buyer.food.FoodData;
import com.rajebdev.kuymakan.buyer.info.InfoData;
import com.rajebdev.kuymakan.buyer.info.InfoListAdapter;
import com.rajebdev.kuymakan.restaurant.RestaurantActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private BottomSheetBehavior mBehavior;

    private ArrayList<FoodData> orderFoods;
    private int restaurantId;

    OrderListBottomSheetDialogFragment(ArrayList<FoodData> orderFoods, int restaurantId) {
        this.orderFoods = orderFoods;
        this.restaurantId = restaurantId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = View.inflate(getContext(), R.layout.bottomsheet_order_list, null);

        LinearLayout linearLayout = view.findViewById(R.id.root);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        linearLayout.setLayoutParams(params);


        Button btnCheckoutOrder = view.findViewById(R.id.btn_checkout_order);
        btnCheckoutOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext());
                alertDialog.setTitle("Insert Location (Lokasi Manual Karena Belum bisa pakek maps)");
                alertDialog.setMessage("Enter Location");

                final EditText input = new EditText(requireContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input); // uncomment this line

                alertDialog.setPositiveButton("ORDER", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        OrderBody orderBody = new OrderBody();
                        orderBody.setFoodorders(orderFoods);
                        orderBody.setLocationSend(input.getText().toString());
                        orderBody.setRestaurantId(restaurantId);
                        SharedPreferences preferences = getActivity().getSharedPreferences("com.rajebdev.kuymakan", Context.MODE_PRIVATE);
                        String token = preferences.getString("tokenLogin", null);
                        RestClient.getService(getContext()).checkoutOrder(orderBody, token).enqueue(new Callback<OrderCheckoutResponse>() {
                            @Override
                            public void onResponse(Call<OrderCheckoutResponse> call, Response<OrderCheckoutResponse> response) {
                                if (response.isSuccessful()) {
                                    int status = response.body().getStatus();
                                    Log.i("Checkout", "onResponse: "+status);
                                    if (status == 1)
                                        Toast.makeText(getContext(), "Order Berhasil, liHat di menu order", Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(getContext(), "Order Gagal, Silahkan Hubungi Pengembang", Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<OrderCheckoutResponse> call, Throwable t) {
                                Log.e("Checkout", "Gagal Load "+t.toString());
                            }
                        });
                    }
                });

                alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });

        // Setup Recycle View
        setUpRecycleView(view);

        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());

        return dialog;
    }

    private void setUpRecycleView(View view) {
        // Set Data For Recycle View
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_checkout_list);
        mRecyclerView.setLayoutManager(layoutManager);
        OrderCheckoutListAdapter mListadapter = new OrderCheckoutListAdapter(orderFoods, this);
        mRecyclerView.setAdapter(mListadapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


}