package com.rajebdev.kuymakan.coupon;

import android.app.Dialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.rajebdev.kuymakan.R;

import java.util.Objects;

public class NewCouponDialogFragment extends DialogFragment {

    public static String TAG = "FullScreenDialog";
    private EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_fragment_new_coupon, container, false);

        // Tombol Close
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });

        // Edit Text Capitalize
        editText = view.findViewById(R.id.input_code_coupon);
        editText.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        // Tombol delete text
        ImageButton btnClearCode = view.findViewById(R.id.btn_clear_code);
        btnClearCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

        // Buttom Pakai
        Button btnUseNewCoupon = view.findViewById(R.id.btn_use_new_coupon);
        btnUseNewCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Code Sedang diproses", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            Objects.requireNonNull(dialog.getWindow()).setLayout(width, height);
        }
    }
}