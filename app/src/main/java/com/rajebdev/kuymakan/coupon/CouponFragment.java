package com.rajebdev.kuymakan.coupon;

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

import com.rajebdev.kuymakan.R;

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
                assert getFragmentManager() != null;
                new NewCouponDialogFragment().show(getFragmentManager().beginTransaction(), NewCouponDialogFragment.TAG);
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
        CouponListAdapter mListadapter = new CouponListAdapter(data, this);
        mRecyclerView.setAdapter(mListadapter);

        return view;
    }

    private void showCouponDetail(CouponData couponData)
    {
    }
}