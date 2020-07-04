package com.rajebdev.kuymakan.buyer.foodsection;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.buyer.food.FoodData;

public class FoodDetailBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private BottomSheetBehavior mBehavior;
    private FoodData foodData;

    FoodDetailBottomSheetDialogFragment(FoodData foodData) {
        this.foodData = foodData;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = View.inflate(getContext(), R.layout.bottomsheet_food_detail, null);

        TextView foodDetailName = view.findViewById(R.id.food_detail_name);
        foodDetailName.setText(foodData.getNames());
        TextView foodPrice = view.findViewById(R.id.food_detail_price);
        foodPrice.setText(String.valueOf(foodData.getPrices()));
        ImageView foodImage = view.findViewById(R.id.image_food_detail);
        Glide.with(requireContext()).load("http://192.168.0.103/apikuymakan/img/foods/dummy.png").into(foodImage);


        LinearLayout linearLayout = view.findViewById(R.id.root);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        linearLayout.setLayoutParams(params);

        // Tombol Share
        View btnRestaurantShare = view.findViewById(R.id.btn_share_food);
        btnRestaurantShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareSub = "KuyMakan";
                String shareBody = "Bagikan Food";
                intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "Share With"));
            }
        });

        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


}