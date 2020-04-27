package com.rajebdev.kuymakan.restaurant.transaction;

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

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.ViewHolder>
{
    private Fragment parrent;
    private ArrayList<TransactionData> dataList;

    public TransactionListAdapter(ArrayList<TransactionData> data, Fragment parrent)
    {
        this.dataList = data;
        this.parrent = parrent;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView bottomTransaction;
        ViewHolder(View itemView)
        {
            super(itemView);
            bottomTransaction = itemView.findViewById(R.id.bottom_transaction);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        // For Last Child Margin
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (position == dataList.size()-1){
            params.bottomMargin = 30;
            holder.bottomTransaction.setVisibility(View.VISIBLE);
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
                Toast.makeText(parrent.getContext(), "Detail Transaction "+ position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }
}