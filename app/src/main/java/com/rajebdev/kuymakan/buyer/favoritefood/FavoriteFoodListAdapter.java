package com.rajebdev.kuymakan.buyer.favoritefood;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;

import java.util.ArrayList;

public class FavoriteFoodListAdapter extends RecyclerView.Adapter<FavoriteFoodListAdapter.ViewHolder>
{
    private ArrayList<FavoriteFoodData> dataList;
    private Fragment parent;

    FavoriteFoodListAdapter(ArrayList<FavoriteFoodData> data, Fragment parent)
    {
        this.dataList = data;
        this.parent = parent;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView sellerName;
        TextView sellerAddress;
        TextView sellerOpenClose;
        TextView sellerClockOpen;
        View btnSellerDetail;

        ViewHolder(View itemView)
        {
            super(itemView);
            this.sellerName = itemView.findViewById(R.id.seller_name);
            this.sellerAddress = itemView.findViewById(R.id.seller_address);
            this.sellerOpenClose = itemView.findViewById(R.id.seller_open_close);
            this.sellerClockOpen = itemView.findViewById(R.id.seller_clock_open);
            this.btnSellerDetail = itemView.findViewById(R.id.btn_seller_detail);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_favorite_food, parent, false);
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
        holder.btnSellerDetail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(parent.getActivity(), "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }
}