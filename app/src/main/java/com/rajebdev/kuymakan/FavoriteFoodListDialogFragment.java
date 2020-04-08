package com.rajebdev.kuymakan;

import android.app.Dialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class FavoriteFoodListDialogFragment extends DialogFragment {

    static String TAG = "FullScreenDialog";
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
        ListAdapter mListadapter = new ListAdapter(data);
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

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<FavoriteFoodData> dataList;

        ListAdapter(ArrayList<FavoriteFoodData> data)
        {
            this.dataList = data;
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView sellerName;
            TextView sellerAddress;
            TextView sellerOpenClose;
            TextView sellerClockOpen;
            View btnSellerDetail;

            ViewHolder(View itemView)
            {
                super(itemView);
                this.sellerName = itemView.findViewById(R.id.seller_name);
                this.sellerAddress = itemView.findViewById(R.id.seller_address);
                this.sellerOpenClose = itemView.findViewById(R.id.seller_open_close);
                this.sellerClockOpen = itemView.findViewById(R.id.seller_clock_open);
                this.btnSellerDetail = itemView.findViewById(R.id.btn_seller_detail);
            }
        }

        @NonNull
        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_favorite_food, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position)
        {
            // For Last Child Margin
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            if (position == dataList.size()-1){
                params.bottomMargin = 30;
            }else{
                params.bottomMargin = 0;
            }
            holder.itemView.setLayoutParams(params);

            // Setter Text
            holder.btnSellerDetail.setOnClickListener(new View.OnClickListener()
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