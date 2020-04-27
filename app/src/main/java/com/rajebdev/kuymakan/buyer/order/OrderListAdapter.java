package com.rajebdev.kuymakan.buyer.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder>
{
    private Fragment parrent;
    private ArrayList<OrderData> dataList;

    public OrderListAdapter(ArrayList<OrderData> data, Fragment parrent)
    {
        this.dataList = data;
        this.parrent = parrent;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView orderTitle;
        TextView orderStatus;
        TextView orderTime;
        CircleImageView orderImage;


        ViewHolder(View itemView)
        {
            super(itemView);
            this.orderTitle = itemView.findViewById(R.id.order_title);
            this.orderStatus = itemView.findViewById(R.id.order_status);
            this.orderTime = itemView.findViewById(R.id.order_time);
            this.orderImage = itemView.findViewById(R.id.order_image);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
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
                assert parrent.getFragmentManager() != null;
                new OrderDetailBottomSheetDialogFragment().show(parrent.getFragmentManager().beginTransaction(), "Order Detail");
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }
}