package com.rajebdev.kuymakan.restaurant;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rajebdev.kuymakan.R;

public class RestaurantDiscountFullBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private BottomSheetBehavior mBehavior;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = View.inflate(getContext(), R.layout.bottomsheet_restaurant_discount, null);

        LinearLayout linearLayout = view.findViewById(R.id.root);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        params.height = getScreenHeight();
        linearLayout.setLayoutParams(params);


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