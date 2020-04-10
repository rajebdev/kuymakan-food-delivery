package com.rajebdev.kuymakan.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;

import java.util.ArrayList;

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

        ArrayList<OrderData> data = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            data.add(new OrderData());
        }
        OrderListAdapter mListadapter = new OrderListAdapter(data, this);
        mRecyclerView.setAdapter(mListadapter);
    }
}
