package com.rajebdev.kuymakan.favoritefood;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rajebdev.kuymakan.R;

import java.util.ArrayList;
import java.util.Objects;

public class FavoriteFoodListDialogFragment extends DialogFragment {

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
        View view = inflater.inflate(R.layout.dialog_fragment_favorite_food_list, container, false);

        // Tombol Close
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // Proses Recycle View List Favorite
        setFavoriteListView(view);

        return view;
    }

    private void setFavoriteListView(View view) {
        // Set Data For Recycle View
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_favorite_food_list);
        mRecyclerView.setLayoutManager(layoutManager);

        ArrayList<FavoriteFoodData> data = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            data.add(
                    new FavoriteFoodData());
        }
        FavoriteFoodListAdapter mListadapter = new FavoriteFoodListAdapter(data, this);
        mRecyclerView.setAdapter(mListadapter);
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