package com.rajebdev.kuymakan.buyer.menu;

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
import com.rajebdev.kuymakan.buyer.profile.EditProfileDialogFragment;
import com.rajebdev.kuymakan.buyer.profile.InviteFriendsDialogFragment;
import com.rajebdev.kuymakan.buyer.coupon.NewCouponDialogFragment;
import com.rajebdev.kuymakan.other.AboutUsDialogFragment;
import com.rajebdev.kuymakan.buyer.profile.HelpDialogFragment;
import com.rajebdev.kuymakan.other.PrivacyDialogFragment;

public class ProfileFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_profile, container, false);

        View btnProfileEdit = view.findViewById(R.id.btn_profile_edit);
        btnProfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                new EditProfileDialogFragment().show(getFragmentManager().beginTransaction(), EditProfileDialogFragment.TAG);
            }
        });

        View btnProfileNewCoupon = view.findViewById(R.id.btn_profile_new_coupon);
        btnProfileNewCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                new NewCouponDialogFragment().show(getFragmentManager().beginTransaction(), NewCouponDialogFragment.TAG);
            }
        });

        View btnProfileInviteFriends = view.findViewById(R.id.btn_profile_invite_friends);
        btnProfileInviteFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                new InviteFriendsDialogFragment().show(getFragmentManager().beginTransaction(), InviteFriendsDialogFragment.TAG);
            }
        });


        View btnProfileHelp = view.findViewById(R.id.btn_profile_help);
        btnProfileHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                new HelpDialogFragment().show(getFragmentManager().beginTransaction(), HelpDialogFragment.TAG);
            }
        });

        View btnProfilePrivacy = view.findViewById(R.id.btn_profile_privacy);
        btnProfilePrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                new PrivacyDialogFragment().show(getFragmentManager().beginTransaction(), PrivacyDialogFragment.TAG);
            }
        });

        View btnProfileAboutUs = view.findViewById(R.id.btn_profile_about_us);
        btnProfileAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                new AboutUsDialogFragment().show(getFragmentManager().beginTransaction(), AboutUsDialogFragment.TAG);
            }
        });

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


        return view;
    }
}
