package com.rajebdev.kuymakan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CouponFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon, container, false);

        // Set Click Button New Coupon
        ImageButton btnAddNewCoupon = view.findViewById(R.id.btn_add_coupon);
        btnAddNewCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewCouponDialogFragment dialogFragmentNewCoupon = new NewCouponDialogFragment();
                assert getFragmentManager() != null;
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialogFragmentNewCoupon.show(ft, NewCouponDialogFragment.TAG);
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

    private void showCouponDetail(CouponData couponData)
    {
        CouponDetailDialogFragment dialogFragmentNewCoupon = new CouponDetailDialogFragment();
        assert getFragmentManager() != null;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        dialogFragmentNewCoupon.show(ft, CouponDetailDialogFragment.TAG);
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

            // Set Onclick coupon
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCouponDetail(dataList.get(position));
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