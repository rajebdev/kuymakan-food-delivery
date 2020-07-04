package com.rajebdev.kuymakan.buyer.restaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.buyer.food.FoodData;

import java.util.ArrayList;

public class OrderCheckoutListAdapter extends RecyclerView.Adapter<OrderCheckoutListAdapter.ViewHolder>
{
    private Fragment parrent;
    private ArrayList<FoodData> dataList;

    public OrderCheckoutListAdapter(ArrayList<FoodData> data, Fragment parrent)
    {
        this.dataList = data;
        this.parrent = parrent;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView foodImage;
        TextView foodName;
        TextView foodPrice;
        TextView foodDesc;
        ViewHolder(View itemView)
        {
            super(itemView);
            foodImage = itemView.findViewById(R.id.item_image_food);
            foodName = itemView.findViewById(R.id.item_food_name);
            foodPrice = itemView.findViewById(R.id.item_food_price);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_item_food_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        // For Last Child Margin
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (position == dataList.size()-1){
            params.rightMargin = 40;
        }else{
            params.rightMargin = 0;
        }
        holder.itemView.setLayoutParams(params);

        holder.foodName.setText(dataList.get(position).getNames());
        holder.foodPrice.setText(String.valueOf(dataList.get(position).getPrices()));
        Glide.with(parrent.requireContext()).load("http://192.168.0.103/apikuymakan/img/foods/dummy.png").into(holder.foodImage);

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