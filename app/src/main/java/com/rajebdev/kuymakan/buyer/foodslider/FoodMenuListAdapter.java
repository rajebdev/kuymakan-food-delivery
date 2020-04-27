package com.rajebdev.kuymakan.buyer.foodslider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.food.FoodData;
import com.rajebdev.kuymakan.buyer.restaurant.RestaurantDialogFragment;

import java.util.ArrayList;

public class FoodMenuListAdapter extends RecyclerView.Adapter<FoodMenuListAdapter.ViewHolder>
{
    private Fragment parrent;
    private ArrayList<FoodData> dataList;

    FoodMenuListAdapter(ArrayList<FoodData> data, Fragment parrent)
    {
        this.dataList = data;
        this.parrent = parrent;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_col_food_menu_slider, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        // For Last Child Margin
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (position == dataList.size()-1){
            params.rightMargin = 36;
        }else{
            params.rightMargin = 0;
        }
        holder.itemView.setLayoutParams(params);

        // Setter Text
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                assert parrent.getFragmentManager() != null;
                new RestaurantDialogFragment().show(parrent.getFragmentManager().beginTransaction(), RestaurantDialogFragment.TAG);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }
}