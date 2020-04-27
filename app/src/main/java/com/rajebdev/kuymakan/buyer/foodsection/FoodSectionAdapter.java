package com.rajebdev.kuymakan.buyer.foodsection;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.Section;
import com.shuhart.stickyheader.StickyAdapter;

import java.util.ArrayList;
import java.util.List;

public class FoodSectionAdapter extends StickyAdapter<RecyclerView.ViewHolder, RecyclerView.ViewHolder> {
    private Fragment parent;
    public List<Section> items = new ArrayList<>();

    public FoodSectionAdapter(Fragment parent){
        this.parent = parent;
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
        int type = items.get(position).type();
        int section = items.get(position).sectionPosition();
        if (type == Section.HEADER) {
            ((HeaderViewholder) holder).textView.setText("Rekomendasi " + section);
        } else if (type == Section.CUSTOM_HEADER) {
            //
        }else if (type == Section.ITEM){
            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

            // Setup ItemView Click
            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    assert parent.getFragmentManager() != null;
                    new FoodDetailBottomSheetDialogFragment().show(parent.getFragmentManager().beginTransaction(), "Food Detail");
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
                    itemViewHolder.textFoodCount.setText(String.valueOf(Integer.parseInt(itemViewHolder.textFoodCount.getText().toString()) + 1));
                }
            });

            itemViewHolder.btnAddFoodCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemViewHolder.textFoodCount.setText(String.valueOf(Integer.parseInt(itemViewHolder.textFoodCount.getText().toString()) + 1));
                }
            });

            itemViewHolder.btnSubFoodCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int foodCount = Integer.parseInt(itemViewHolder.textFoodCount.getText().toString()) - 1;
                    itemViewHolder.textFoodCount.setText(String.valueOf(foodCount));
                    if (foodCount == 0) {
                        itemViewHolder.btnChangeFoodCount.setVisibility(View.GONE);
                        itemViewHolder.btnAddToCart.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
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

        HeaderViewholder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.header_food);
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