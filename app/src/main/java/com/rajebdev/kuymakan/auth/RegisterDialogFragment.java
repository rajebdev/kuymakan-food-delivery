package com.rajebdev.kuymakan.auth;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.rajebdev.kuymakan.R;
import com.skydoves.powerspinner.PowerSpinnerView;

public class RegisterDialogFragment extends DialogFragment {
    public static String TAG = "FullScreenDialog";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.dialog_fragment_register, container, false);

        // Spinner
        final PowerSpinnerView userSpinner = view.findViewById(R.id.user_type);

        final EditText edtPhone = view.findViewById(R.id.phone_number);
        // Button Regis
        Button btnRegis = view.findViewById(R.id.btn_regis);
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userType = userSpinner.getSelectedIndex() + 1;
                Log.i("narko", "usertype " + userType);
                new OTPDialogFragment(userType, edtPhone.getText().toString()).show(getChildFragmentManager().beginTransaction(), OTPDialogFragment.TAG);
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
