package com.rajebdev.kuymakan.featured;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.utils.RandomColor;

import java.util.ArrayList;

public class FeaturedListAdapter extends RecyclerView.Adapter<FeaturedListAdapter.ViewHolder>
{
    private Fragment parrent;
    private ArrayList<FeaturedData> dataList;

    public FeaturedListAdapter(ArrayList<FeaturedData> data, Fragment parrent)
    {
        this.dataList = data;
        this.parrent = parrent;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ViewHolder(View itemView)
        {
            super(itemView);
            GradientDrawable drawable = (GradientDrawable) itemView.findViewById(R.id.background_featured).getBackground();
            drawable.setColor(new RandomColor().generateDarkColor());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_col_featured_slider, parent, false);
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