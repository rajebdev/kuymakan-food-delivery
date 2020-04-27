package com.rajebdev.kuymakan.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.rajebdev.kuymakan.R;

public class LoginDialogFragment extends DialogFragment {
    public static String TAG = "FullScreenDialog";
    private int userType;

    LoginDialogFragment(int userType) {
        this.userType = userType;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_fragment_login, container, false);

        // Button SEND OTP
        Button btnSendOTP = view.findViewById(R.id.btn_send_otp);
        btnSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                new OTPDialogFragment(userType).show(getFragmentManager().beginTransaction(), OTPDialogFragment.TAG);
            }
        });

        // Btn Call Us
        View btnCallUs = view.findViewById(R.id.btn_call_us);
        btnCallUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),  "HUBUNGI KAMI BEBERAPA SAAT LAGI", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
