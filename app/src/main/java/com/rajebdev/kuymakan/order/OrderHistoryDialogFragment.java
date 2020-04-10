package com.rajebdev.kuymakan.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;

import java.util.ArrayList;

public class OrderHistoryDialogFragment extends DialogFragment {

    static String TAG = "FullScreenDialog";
    private EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_fragment_order_history, container, false);

        // Tombol Close
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
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
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_order_history_list);
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