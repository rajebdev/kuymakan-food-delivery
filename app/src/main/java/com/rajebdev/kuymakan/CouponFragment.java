package com.rajebdev.kuymakan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Objects;

public class CouponFragment extends Fragment {
    private View view;
    private BottomSheetDialog sheetDialog;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_coupon, container, false);

        // Set Click Button New Coupon
        ImageButton btnAddNewCoupon = view.findViewById(R.id.btn_add_coupon);
        btnAddNewCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragmentNewCoupon dialogFragmentNewCoupon = new DialogFragmentNewCoupon();
                assert getFragmentManager() != null;
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialogFragmentNewCoupon.show(ft, DialogFragmentNewCoupon.TAG);
            }
        });


        // Set Data For Recycle View
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_coupon_list);
        mRecyclerView.setLayoutManager(layoutManager);

        ArrayList<CouponData> data = new ArrayList<CouponData>();
        for (int i = 0; i < 10; i++)
        {
            data.add(
                    new CouponData
                            (
                                    i,
                                    "Narko",
                                    "Voucher GoFood Rp 42.000 pakai Link Aja",
                                    "Berlaku hingga 8 April 2020"
                            ));
        }

        ListAdapter mListadapter = new ListAdapter(data);
        mRecyclerView.setAdapter(mListadapter);

        return view;
    }

    private int getWindowHeight() {
        // Calculate window height for fullscreen use
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) Objects.requireNonNull(getContext())).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    private void showCouponDetailSheet(CouponData couponData)
    {
        View containerSheet = view.findViewById(R.id.coupon_detail_sheet);
        BottomSheetBehavior sheetBehavior = BottomSheetBehavior.from(containerSheet);
        @SuppressLint("InflateParams") View viewSheet = getLayoutInflater().inflate(R.layout.sheet_coupon_detail, null);

        viewSheet.findViewById(R.id.btn_close_coupon_detail).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sheetDialog.dismiss();
            }
        });

        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        sheetDialog = new BottomSheetDialog(Objects.requireNonNull(view.getContext()));
        sheetDialog.setContentView(viewSheet);
        Objects.requireNonNull(sheetDialog.getWindow()).addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        sheetDialog.show();
        sheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                sheetDialog = null;
            }
        });
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<CouponData> dataList;

        ListAdapter(ArrayList<CouponData> data)
        {
            this.dataList = data;
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
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_coupon, parent, false);

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

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCouponDetailSheet(dataList.get(position));
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
                    Toast.makeText(getActivity(), "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
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