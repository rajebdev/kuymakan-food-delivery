package com.rajebdev.kuymakan.buyer.coupon;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;

import java.util.ArrayList;

public class CouponListAdapter extends RecyclerView.Adapter<CouponListAdapter.ViewHolder>
{
    private ArrayList<CouponData> dataList;
    private Fragment parent;

    public CouponListAdapter(ArrayList<CouponData> data, Fragment parent)
    {
        this.dataList = data;
        this.parent = parent;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameCoupon;
        TextView expiredCoupon;
        Button btnUseCoupon;

        ViewHolder(View itemView)
        {
            super(itemView);
            this.nameCoupon = itemView.findViewById(R.id.name_coupon);
            this.expiredCoupon = itemView.findViewById(R.id.expired_coupon);
            this.btnUseCoupon = itemView.findViewById(R.id.btn_use_coupon);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_coupon, parent, false);

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

        // Set Onclick coupon
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert parent.getFragmentManager() != null;
                new CouponDetailDialogFragment().show( parent.getFragmentManager().beginTransaction(), CouponDetailDialogFragment.TAG);
            }
        });

        // Setter Text
        holder.nameCoupon.setText(dataList.get(position).getName());
        holder.expiredCoupon.setText(dataList.get(position).getExpired());

        holder.btnUseCoupon.setOnClickListener(new View.OnClickListener()
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