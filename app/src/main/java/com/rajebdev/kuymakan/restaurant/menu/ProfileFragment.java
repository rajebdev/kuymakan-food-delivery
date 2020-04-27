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

public class ProfileFragment  extends Fragment {
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_profile, container, false);

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
        View btnMenu = view.findViewById(R.id.btn_menu);
        View btnStock = view.findViewById(R.id.btn_stock);
        View btnOutlet = view.findViewById(R.id.btn_outlet);
        View btnHelp = view.findViewById(R.id.btn_help);

        return view;
    }
}