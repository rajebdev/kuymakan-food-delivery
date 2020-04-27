package com.rajebdev.kuymakan.restaurant.menu;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.rajebdev.kuymakan.R;
import com.rajebdev.kuymakan.other.AboutUsDialogFragment;
import com.rajebdev.kuymakan.buyer.profile.HelpDialogFragment;
import com.rajebdev.kuymakan.restaurant.foodmenu.FoodMenuDialogFragment;
import com.rajebdev.kuymakan.restaurant.outlet.OutletDialogFragment;
import com.rajebdev.kuymakan.restaurant.stock.StockDialogFragment;
import com.rajebdev.kuymakan.restaurant.withdraw.WithdrawDialogFragment;

public class OtherFragment extends Fragment {
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_other, container, false);

        // btn version about us
        View btnAboutUs = view.findViewById(R.id.btn_about_us);
        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AboutUsDialogFragment().show(getChildFragmentManager().beginTransaction(), AboutUsDialogFragment.TAG);
            }
        });

        // Btn sign out
        View btnSignOut = view.findViewById(R.id.btn_sign_out);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Konfirmasi Keluar Akun");
                builder.setMessage("Apakah yakin untuk keluar dari Akun ini?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Kamu telah keluar", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.show();
            }
        });

        // btn withdraw
        View btnWithdraw = view.findViewById(R.id.btn_withdraw);
        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WithdrawDialogFragment().show(getChildFragmentManager().beginTransaction(), WithdrawDialogFragment.TAG);
            }
        });

        // Btn food menu
        View btnMenu = view.findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FoodMenuDialogFragment().show(getChildFragmentManager().beginTransaction(), FoodMenuDialogFragment.TAG);
            }
        });
        View btnStock = view.findViewById(R.id.btn_stock);
        btnStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new StockDialogFragment().show(getChildFragmentManager().beginTransaction(), StockDialogFragment.TAG);
            }
        });

        // Btn Outlet
        View btnOutlet = view.findViewById(R.id.btn_outlet);
        btnOutlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new OutletDialogFragment().show(getChildFragmentManager().beginTransaction(), OutletDialogFragment.TAG);
            }
        });

        // Btn help
        View btnHelp = view.findViewById(R.id.btn_help);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HelpDialogFragment().show(getChildFragmentManager().beginTransaction(), HelpDialogFragment.TAG);
            }
        });

        return view;
    }
}