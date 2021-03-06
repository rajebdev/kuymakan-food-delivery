package com.rajebdev.kuymakan.buyer.foodslider;

import android.util.Log;
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
import com.rajebdev.kuymakan.buyer.restaurant.RestaurantDialogFragment;

import java.util.List;

public class FoodMenuListAdapter extends RecyclerView.Adapter<FoodMenuListAdapter.ViewHolder>
{
    private Fragment parrent;
    private List<FoodData> dataList;

    FoodMenuListAdapter(List<FoodData> data, Fragment parrent)
    {
        this.dataList = data;
        this.parrent = parrent;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        View soldOut;
        TextView foodName;
        TextView foodPrice;

        ViewHolder(View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_food_menu);
            soldOut = itemView.findViewById(R.id.sold_out_view);
            foodName = itemView.findViewById(R.id.food_name);
            foodPrice = itemView.findViewById(R.id.food_price);
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

        Glide.with(this.parrent.requireContext()).load("http://192.168.0.103/apikuymakan/img/foods/dummy.png").into(holder.imageView);
        if (dataList.get(position).getStock() > 0){
            holder.soldOut.setVisibility(View.GONE);
        }
        holder.foodName.setText(dataList.get(position).getNames());
        Log.i("Food Menu", "onBindViewHolder: "+dataList.get(position));
        holder.foodPrice.setText("IDR " + dataList.get(position).getPrices());

        final int restaurantid = dataList.get(position).getRestaurantId();

        // Setter Text
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new RestaurantDialogFragment(restaurantid).show(parrent.getFragmentManager().beginTransaction(), RestaurantDialogFragment.TAG);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }
}