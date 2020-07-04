package com.rajebdev.kuymakan.buyer.order;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.RestClient;
import com.rajebdev.kuymakan.buyer.food.FoodData;
import com.rajebdev.kuymakan.buyer.food.FoodViewResponse;
import com.rajebdev.kuymakan.buyer.restaurant.RestaurantData;
import com.rajebdev.kuymakan.buyer.restaurant.RestaurantResponse;
import com.rajebdev.kuymakan.utils.DateConverter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private BottomSheetBehavior mBehavior;
    private OrderData orderData;

    OrderDetailBottomSheetDialogFragment(OrderData orderData) {
        this.orderData = orderData;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = View.inflate(getContext(), R.layout.bottomsheet_order_detail, null);

        LinearLayout linearLayout = view.findViewById(R.id.root);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        params.height = getScreenHeight();
        linearLayout.setLayoutParams(params);

        TextView orderCode = view.findViewById(R.id.order_id);
        orderCode.setText(orderData.getCode());

        TextView orderTime = view.findViewById(R.id.order_time_detail);
        orderTime.setText(new DateConverter().convert(orderData.getCreated()));

        TextView toLocation = view.findViewById(R.id.to_location);
        toLocation.setText(orderData.getLocationSend());

        TextView fromLocation = view.findViewById(R.id.from_location);
        RestClient.getService(getContext()).getRestaurant(orderData.getRestaurantId()).enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                if (response.isSuccessful()){
                    RestaurantData data;
                    data = response.body().getRestaurantData();
                    fromLocation.setText(data.getNames());
                }
            }
            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal Load "+t.toString(), Toast.LENGTH_LONG).show();
            }
        });

        // Fee
        TextView priceEstimate = view.findViewById(R.id.price_estimate);
        priceEstimate.setText("0");

        TextView priceTotal = view.findViewById(R.id.price_total);
        priceTotal.setText("0");
        for (int i = 0; i < orderData.getFoodorders().size(); i++) {
            RestClient.getService(getContext()).getFood(orderData.getFoodorders().get(i).getFoodId()).enqueue(new Callback<FoodViewResponse>() {
                @Override
                public void onResponse(Call<FoodViewResponse> call, Response<FoodViewResponse> response) {
                    if (response.isSuccessful()){
                        FoodData data;
                        data = response.body().getData();
                        priceEstimate.setText(String.valueOf(Integer.parseInt((String) priceEstimate.getText()) + data.getPrices()));
                        priceTotal.setText(String.valueOf(Integer.parseInt((String) priceTotal.getText()) + data.getPrices()));
                    }
                }
                @Override
                public void onFailure(Call<FoodViewResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Gagal Load "+t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        }


        // Setup Recycle View
        setUpRecycleViewOrderDetail(view);

        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        return dialog;
    }

    private void setUpRecycleViewOrderDetail(View view) {
        // Set Data For Recycle View
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_food_order_list);
        mRecyclerView.setLayoutManager(layoutManager);

        ListAdapter mListadapter = new ListAdapter(orderData.getFoodorders());
        mRecyclerView.setAdapter(mListadapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private List<FoodOrderData> dataList;

        ListAdapter(List<FoodOrderData> data)
        {
            this.dataList = data;
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView foodName;
            TextView amount;
            ViewHolder(View itemView)
            {
                super(itemView);
                foodName = itemView.findViewById(R.id.food_name);
                amount = itemView.findViewById(R.id.food_count);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_food_order, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position)
        {
            // For Last Child Margin
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            if (position == dataList.size()-1){
                params.bottomMargin = 30;
            }else{
                params.bottomMargin = 0;
            }
            holder.itemView.setLayoutParams(params);

            // Food Name
            RestClient.getService(getContext()).getFood(dataList.get(position).getFoodId()).enqueue(new Callback<FoodViewResponse>() {
                @Override
                public void onResponse(Call<FoodViewResponse> call, Response<FoodViewResponse> response) {
                    if (response.isSuccessful()){
                        FoodData data;
                        data = response.body().getData();
                        holder.foodName.setText(data.getNames());
                    }
                }
                @Override
                public void onFailure(Call<FoodViewResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Gagal Load "+t.toString(), Toast.LENGTH_LONG).show();
                }
            });
            // Jumlah
            holder.amount.setText(String.valueOf(dataList.get(position).getAmount()));

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

}