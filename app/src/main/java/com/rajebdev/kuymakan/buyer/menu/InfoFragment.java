package com.rajebdev.kuymakan.buyer.menu;

import android.os.Bundle;
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
import com.rajebdev.kuymakan.buyer.info.InfoData;
import com.rajebdev.kuymakan.buyer.info.InfoListAdapter;

import java.util.ArrayList;

public class InfoFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_info, container, false);

        // Setup Delete Button
        ImageButton btnClearInfo = view.findViewById(R.id.btn_clear_info);
        btnClearInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clear Info List", Toast.LENGTH_SHORT).show();
            }
        });

        // Setup Recycle View
        setUpRecycleViewInfo(view);

        return view;
    }

    private void setUpRecycleViewInfo(View view) {
        // Set Data For Recycle View
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_info_list);
        mRecyclerView.setLayoutManager(layoutManager);

        ArrayList<InfoData> data = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            data.add(
                    new InfoData());
        }
        InfoListAdapter mListadapter = new InfoListAdapter(data, this);
        mRecyclerView.setAdapter(mListadapter);
    }
}
