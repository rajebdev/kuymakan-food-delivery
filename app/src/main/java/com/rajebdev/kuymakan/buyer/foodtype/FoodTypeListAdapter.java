package com.rajebdev.kuymakan.buyer.foodtype;

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

import java.util.ArrayList;
import java.util.List;

public class FoodTypeListAdapter extends RecyclerView.Adapter<FoodTypeListAdapter.ViewHolder>
{
    private Fragment parrent;
    private List<FoodTypeData> dataList;

    public FoodTypeListAdapter(List<FoodTypeData> data, Fragment parrent)
    {
        this.dataList = data;
        this.parrent = parrent;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView images;
        TextView label;
        ViewHolder(View itemView)
        {
            super(itemView);
            images = itemView.findViewById(R.id.img_food_slider);
            label = itemView.findViewById(R.id.tv_food_slider);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_col_food_type_slider, parent, false);
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

        holder.label.setText(this.dataList.get(position).getLabel());
        Glide.with(this.parrent.getContext()).load("http://192.168.0.103/apikuymakan/"+dataList.get(position).getImages()).into(holder.images);

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