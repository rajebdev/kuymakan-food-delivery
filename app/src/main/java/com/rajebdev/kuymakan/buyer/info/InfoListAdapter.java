package com.rajebdev.kuymakan.buyer.info;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;

import java.util.ArrayList;

public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.ViewHolder>
{
    private ArrayList<InfoData> dataList;
    private Fragment parent;

    public InfoListAdapter(ArrayList<InfoData> data, Fragment parent)
    {
        this.dataList = data;
        this.parent = parent;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView infoTitle;
        TextView infoTime;
        TextView infoDesc;
        ImageView infoImage;
        ImageView infoLogo;

        ViewHolder(View itemView)
        {
            super(itemView);
            this.infoTitle = itemView.findViewById(R.id.info_title);
            this.infoTime = itemView.findViewById(R.id.info_time);
            this.infoDesc = itemView.findViewById(R.id.info_desc);
            this.infoImage = itemView.findViewById(R.id.info_image);
            this.infoLogo = itemView.findViewById(R.id.info_logo);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_info, parent, false);
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
                InfoDetailDialogFragment infoDetailDialogFragment = new InfoDetailDialogFragment();
                assert parent.getFragmentManager() != null;
                FragmentTransaction ft = parent.getFragmentManager().beginTransaction();
                infoDetailDialogFragment.show(ft, InfoDetailDialogFragment.TAG);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }
}