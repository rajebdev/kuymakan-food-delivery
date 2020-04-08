package com.rajebdev.kuymakan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        // Setup Button History Order
        ImageButton btnOrderHistory = view.findViewById(R.id.btn_order_history);
        btnOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderHistoryDialogFragment orderHistoryDialogFragment = new OrderHistoryDialogFragment();
                assert getFragmentManager() != null;
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                orderHistoryDialogFragment.show(ft, OrderHistoryDialogFragment.TAG);
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

        ArrayList<OrderData> data = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            data.add(new OrderData());
        }
        OrderListAdapter mListadapter = new OrderListAdapter(data, this);
        mRecyclerView.setAdapter(mListadapter);
    }
}
