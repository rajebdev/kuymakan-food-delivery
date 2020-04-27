package com.rajebdev.kuymakan.restaurant.outlet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.restaurant.withdraw.WithdrawDialogFragment;

public class OutletDialogFragment extends DialogFragment {
    public static String TAG = "FullScreenDialog";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_fragment_outlet, container, false);

        // Tombol Close
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // Button Listener
        View btnContact = view.findViewById(R.id.btn_contact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ContactDialogFragment().show(getChildFragmentManager(), ContactDialogFragment.TAG);
            }
        });
        View btnAddress = view.findViewById(R.id.btn_address);
        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddressDialogFragment().show(getChildFragmentManager(), AddressDialogFragment.TAG);
            }
        });
        View btnWithdrawAccount = view.findViewById(R.id.btn_withdraw_account);
        btnWithdrawAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WithdrawAccountDialogFragment().show(getChildFragmentManager(), WithdrawAccountDialogFragment.TAG);
            }
        });
        View btnProfileDetail = view.findViewById(R.id.btn_profile_detail);
        btnProfileDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ProfileDialogFragment().show(getChildFragmentManager(), ProfileDialogFragment.TAG);
            }
        });
        View btnSchedule = view.findViewById(R.id.btn_schedule);
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ScheduleDialogFragment().show(getChildFragmentManager(), ScheduleDialogFragment.TAG);
            }
        });

        return view;
    }
}