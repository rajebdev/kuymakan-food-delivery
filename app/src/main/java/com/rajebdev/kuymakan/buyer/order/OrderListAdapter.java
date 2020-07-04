package com.rajebdev.kuymakan.buyer.order;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.RestClient;
import com.rajebdev.kuymakan.buyer.restaurant.RestaurantData;
import com.rajebdev.kuymakan.buyer.restaurant.RestaurantResponse;
import com.rajebdev.kuymakan.utils.DateConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder>
{
    private Fragment parrent;
    private List<OrderData> dataList;

    public OrderListAdapter(List<OrderData> data, Fragment parrent)
    {
        this.dataList = data;
        this.parrent = parrent;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView orderTitle;
        TextView orderStatus;
        TextView orderTime;
        CircleImageView orderImage;

        ViewHolder(View itemView)
        {
            super(itemView);
            this.orderTitle = itemView.findViewById(R.id.order_title);
            this.orderStatus = itemView.findViewById(R.id.order_status);
            this.orderTime = itemView.findViewById(R.id.order_time);
            this.orderImage = itemView.findViewById(R.id.order_image);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_order, parent, false);
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

        // Tanggal
        holder.orderTime.setText(new DateConverter().convert(dataList.get(position).getCreated()));

        // Status
        RestClient.getService(parrent.getContext()).getOrderStatus(dataList.get(position).getOrderstatusId()).enqueue(new Callback<OrderStatusResponse>() {
            @Override
            public void onResponse(Call<OrderStatusResponse> call, Response<OrderStatusResponse> response) {
                if (response.isSuccessful()){
                    OrderStatusData data;
                    data = response.body().getOrderStatusData();
                    holder.orderStatus.setText(data.getLabel());
                }
            }
            @Override
            public void onFailure(Call<OrderStatusResponse> call, Throwable t) {
                Toast.makeText(parrent.getContext(), "Gagal Load "+t.toString(), Toast.LENGTH_LONG).show();
            }
        });

        // Name
        RestClient.getService(parrent.getContext()).getRestaurant(dataList.get(position).getRestaurantId()).enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                if (response.isSuccessful()){
                    RestaurantData data;
                    data = response.body().getRestaurantData();
                    holder.orderTitle.setText(data.getNames());
                }
            }
            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                Toast.makeText(parrent.getContext(), "Gagal Load "+t.toString(), Toast.LENGTH_LONG).show();
            }
        });


        // Setter Text
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new OrderDetailBottomSheetDialogFragment(dataList.get(position)).show(parrent.getChildFragmentManager().beginTransaction(), "Order Detail");
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return dataList.size();
    }
}