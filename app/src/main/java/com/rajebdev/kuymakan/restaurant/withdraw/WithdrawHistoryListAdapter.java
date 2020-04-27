package com.rajebdev.kuymakan.restaurant.withdraw;

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

public class WithdrawHistoryListAdapter  extends RecyclerView.Adapter<WithdrawHistoryListAdapter.ViewHolder>
{
    private Fragment parrent;
    private ArrayList<WithdrawHistoryData> dataList;

    public WithdrawHistoryListAdapter(ArrayList<WithdrawHistoryData> data, Fragment parrent)
    {
        this.dataList = data;
        this.parrent = parrent;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView bottomInfo;
        ViewHolder(View itemView)
        {
            super(itemView);
            bottomInfo = itemView.findViewById(R.id.bottom_info);
        }
    }

    @NonNull
    @Override
    public WithdrawHistoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_withdraw_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        // For Last Child Margin
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (position == dataList.size()-1){
            params.bottomMargin = 30;
            holder.bottomInfo.setVisibility(View.VISIBLE);
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
                Toast.makeText(parrent.getContext(), "Detail Withdraw "+ position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }
}