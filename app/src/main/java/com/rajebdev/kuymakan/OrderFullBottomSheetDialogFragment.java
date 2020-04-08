package com.rajebdev.kuymakan;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class OrderFullBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private BottomSheetBehavior mBehavior;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = View.inflate(getContext(), R.layout.sheet_order_detail, null);

        LinearLayout linearLayout = view.findViewById(R.id.root);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        params.height = getScreenHeight();
        linearLayout.setLayoutParams(params);

        // Setup Recycle View
        setUpRecycleViewOrderDetail(view);

        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        return dialog;
    }

    private void setUpRecycleViewOrderDetail(View view) {
        // Set Data For Recycle View
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_food_order_list);
        mRecyclerView.setLayoutManager(layoutManager);

        ArrayList<FoodOrderData> data = new ArrayList<>();
        for (int i = 0; i < 3; i++)
        {
            data.add(new FoodOrderData());
        }
        ListAdapter mListadapter = new ListAdapter(data);
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


    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<FoodOrderData> dataList;

        ListAdapter(ArrayList<FoodOrderData> data)
        {
            this.dataList = data;
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {

            ViewHolder(View itemView)
            {
                super(itemView);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_food_order, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position)
        {
            // For Last Child Margin
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            if (position == dataList.size()-1){
                params.bottomMargin = 30;
            }else{
                params.bottomMargin = 0;
            }
            holder.itemView.setLayoutParams(params);

            // Setter Text
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                }
            });
        }

        @Override
        public int getItemCount()
        {
            return dataList.size();
        }
    }

}