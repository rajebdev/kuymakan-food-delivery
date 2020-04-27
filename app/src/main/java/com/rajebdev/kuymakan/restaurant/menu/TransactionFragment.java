package com.rajebdev.kuymakan.restaurant.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.restaurant.transaction.TransactionData;
import com.rajebdev.kuymakan.restaurant.transaction.TransactionListAdapter;

import java.util.ArrayList;

public class TransactionFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_transaction, container, false);
        // Setup Recycle View
        setUpRecycleViewTransaction(view);
        return view;
    }


    private void setUpRecycleViewTransaction(View view) {
        // Set Data For Recycle View
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_transaction_list);
        mRecyclerView.setLayoutManager(layoutManager);

        ArrayList<TransactionData> data = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            data.add(new TransactionData());
        }
        TransactionListAdapter listAdapter = new TransactionListAdapter(data, this);
        mRecyclerView.setAdapter(listAdapter);
    }
}