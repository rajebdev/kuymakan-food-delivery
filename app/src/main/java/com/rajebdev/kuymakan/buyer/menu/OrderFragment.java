package com.rajebdev.kuymakan.buyer.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.RestClient;
import com.rajebdev.kuymakan.buyer.foodtype.FoodTypeData;
import com.rajebdev.kuymakan.buyer.foodtype.FoodTypeListAdapter;
import com.rajebdev.kuymakan.buyer.foodtype.FoodTypeRespond;
import com.rajebdev.kuymakan.buyer.order.OrderData;
import com.rajebdev.kuymakan.buyer.order.OrderHistoryDialogFragment;
import com.rajebdev.kuymakan.buyer.order.OrderListAdapter;
import com.rajebdev.kuymakan.buyer.order.OrderResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_order, container, false);

        // Setup Button History Order
        ImageButton btnOrderHistory = view.findViewById(R.id.btn_order_history);
        btnOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                new OrderHistoryDialogFragment().show(getFragmentManager().beginTransaction(), OrderHistoryDialogFragment.TAG);
            }
        });

        // Setup Recycle View
        setUpRecycleViewOrder(view);

        return view;
    }

    private void setUpRecycleViewOrder(View view) {
        // Set Data For Recycle View
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_order_list);
        mRecyclerView.setLayoutManager(layoutManager);

        SharedPreferences preferences = getActivity().getSharedPreferences("com.rajebdev.kuymakan", Context.MODE_PRIVATE);
        String token = preferences.getString("tokenLogin", null);

        final Fragment main = this;

        RestClient.getService(getContext()).getlistOrder(token).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response.isSuccessful()){
                    List<OrderData> listItem;
                    listItem = response.body().getData();
                    Log.i("Order", "onResponse: "+listItem);
                    OrderListAdapter mListadapter = new OrderListAdapter(listItem, main);
                    mRecyclerView.setAdapter(mListadapter);
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal Load "+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
