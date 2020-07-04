package com.rajebdev.kuymakan.buyer.foodsection;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.RestClient;
import com.rajebdev.kuymakan.Section;
import com.rajebdev.kuymakan.buyer.food.FoodData;
import com.rajebdev.kuymakan.buyer.restaurant.RestaurantData;
import com.rajebdev.kuymakan.buyer.restaurant.RestaurantResponse;
import com.shuhart.stickyheader.StickyAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodSectionAdapter extends StickyAdapter<RecyclerView.ViewHolder, RecyclerView.ViewHolder> {
    private Fragment parent;
    public List<Section> items = new ArrayList<>();

    private RestaurantData restaurantData;
    private  int restaurantId;

    public ArrayList<FoodData> orderFoods = new ArrayList<>();

    public FoodSectionAdapter(Fragment parent, int restaurantId){
        this.parent = parent;
        this.restaurantId = restaurantId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == Section.HEADER) {
            return new HeaderViewholder(inflater.inflate(R.layout.item_row_header_food, parent, false));
        }
        else if (viewType == Section.CUSTOM_HEADER){
            return new HeaderViewholder(inflater.inflate(R.layout.item_row_restaurant_header, parent, false));
        }
        return new ItemViewHolder(inflater.inflate(R.layout.item_row_item_food, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RestClient.getService(null).getRestaurant(restaurantId).enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                if (response.isSuccessful()){
                    restaurantData =  response.body().getRestaurantData();
                    int type = items.get(position).type();
                    int section = items.get(position).sectionPosition();
                    int foodId = items.get(position).getId();
                    if (type == Section.HEADER) {
                        ((HeaderViewholder) holder).textView.setText("Rekomendasi " + section);
                    } else if (type == Section.CUSTOM_HEADER) {
                        ((HeaderViewholder) holder).restName.setText(restaurantData.getNames());
                    }else if (type == Section.ITEM){
                        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                        itemViewHolder.foodName.setText((restaurantData.getFoods().get(foodId).getNames()));
                        itemViewHolder.foodPrice.setText(String.valueOf(restaurantData.getFoods().get(foodId).getPrices()));
                        Glide.with(parent.requireContext()).load("http://192.168.0.103/apikuymakan/img/foods/dummy.png").into(itemViewHolder.foodImage);
                        // Setup ItemView Click
                        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new FoodDetailBottomSheetDialogFragment((restaurantData.getFoods().get(foodId))).show(parent.getChildFragmentManager().beginTransaction(), "Food Detail");
                            }
                        });

                        // Setup Price
                        if (Integer.parseInt(itemViewHolder.foodPrice.getText().toString().replace(".", "")) > 0){
                            itemViewHolder.foodDiscountPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                        }

                        // Setting Button Listener
                        itemViewHolder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                itemViewHolder.btnAddToCart.setVisibility(View.GONE);
                                itemViewHolder.btnChangeFoodCount.setVisibility(View.VISIBLE);
                                orderFoods.add(restaurantData.getFoods().get(foodId));
                                itemViewHolder.textFoodCount.setText(String.valueOf(Integer.parseInt(itemViewHolder.textFoodCount.getText().toString()) + 1));
                            }
                        });

                        itemViewHolder.btnAddFoodCount.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(parent.requireContext(), "Only can order 1 amount of Every Food", Toast.LENGTH_LONG).show();
                                itemViewHolder.textFoodCount.setText(String.valueOf(Integer.parseInt(itemViewHolder.textFoodCount.getText().toString()) + 0));
                            }
                        });

                        itemViewHolder.btnSubFoodCount.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int foodCount = Integer.parseInt(itemViewHolder.textFoodCount.getText().toString()) - 1;
                                itemViewHolder.textFoodCount.setText(String.valueOf(foodCount));
                                orderFoods.remove(restaurantData.getFoods().get(foodId));
                                Log.i("Sub", "onClick: "+orderFoods.size());
                                if (foodCount == 0) {
                                    itemViewHolder.btnChangeFoodCount.setVisibility(View.GONE);
                                    itemViewHolder.btnAddToCart.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).type();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getHeaderPositionForItem(int itemPosition) {
        return items.get(itemPosition).sectionPosition();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int headerPosition) {
        ((HeaderViewholder) holder).textView.setText("Header");
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return createViewHolder(parent, Section.HEADER);
    }

    public static class HeaderViewholder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView restName;

        HeaderViewholder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.header_food);
            restName = itemView.findViewById(R.id.restaurant_name_scroll);
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView foodName;
        TextView foodDesc;
        TextView foodDiscountPrice;
        TextView foodPrice;
        ImageView foodImage;
        View btnAddToCart;
        View btnFavoriteFood;
        View btnChangeFoodCount;
        View btnSubFoodCount;
        View btnAddFoodCount;
        TextView textFoodCount;

        ItemViewHolder(View itemView) {
            super(itemView);

            // Setup Text View Data
            foodName = itemView.findViewById(R.id.item_food_name);
            foodDesc = itemView.findViewById(R.id.item_food_desc);
            foodImage = itemView.findViewById(R.id.item_image_food);
            foodDiscountPrice = itemView.findViewById(R.id.item_food_discount_price);
            foodPrice = itemView.findViewById(R.id.item_food_price);

            // Setup Button
            btnFavoriteFood = itemView.findViewById(R.id.btn_favorite_food);
            btnAddToCart = itemView.findViewById(R.id.btn_add_to_cart);

            // Setup Food Count
            btnChangeFoodCount = itemView.findViewById(R.id.btn_change_food_count);
            textFoodCount = itemView.findViewById(R.id.text_food_count);
            btnSubFoodCount = btnChangeFoodCount.findViewById(R.id.btn_sub_food_count);
            btnAddFoodCount = btnChangeFoodCount.findViewById(R.id.btn_add_food_count);
        }
    }
}