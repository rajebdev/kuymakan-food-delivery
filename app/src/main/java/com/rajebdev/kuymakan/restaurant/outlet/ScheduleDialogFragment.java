package com.rajebdev.kuymakan.restaurant.outlet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.restaurant.foodmenu.FoodMenuData;
import com.rajebdev.kuymakan.restaurant.foodmenu.FoodMenuListAdapter;

import java.util.ArrayList;

public class ScheduleDialogFragment extends DialogFragment {
    public static String TAG = "FullScreenDialog";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_fragment_outlet_schedule, container, false);

        // Tombol Close
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setUpRecycleView(view);
        return view;
    }


    private void setUpRecycleView(View view) {
        // Set Data For Recycle View
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_schedule_list);
        mRecyclerView.setLayoutManager(layoutManager);

        ArrayList<ScheduleData> data = new ArrayList<>();
        for (int i = 0; i < 7; i++)
        {
            data.add(new ScheduleData());
        }
        ScheduleListAdapter listAdapter = new ScheduleListAdapter(data, this);
        mRecyclerView.setAdapter(listAdapter);
    }
}