package com.rajebdev.kuymakan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InfoFragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        // Setup Delete Button
        ImageButton btnClearInfo = view.findViewById(R.id.btn_clear_info);
        btnClearInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clear Info List", Toast.LENGTH_SHORT).show();
            }
        });

        // Setup Recycle View
        setUpRecycleViewInfo(view);

        return view;
    }

    private void setUpRecycleViewInfo(View view) {
        // Set Data For Recycle View
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView mRecyclerView = view.findViewById(R.id.rv_info_list);
        mRecyclerView.setLayoutManager(layoutManager);

        ArrayList<InfoData> data = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            data.add(
                    new InfoData());
        }
        ListAdapter mListadapter = new ListAdapter(data);
        mRecyclerView.setAdapter(mListadapter);
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<InfoData> dataList;

        ListAdapter(ArrayList<InfoData> data)
        {
            this.dataList = data;
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView infoTitle;
            TextView infoTime;
            TextView infoDesc;
            ImageView infoImage;
            ImageView infoLogo;

            ViewHolder(View itemView)
            {
                super(itemView);
                this.infoTitle = itemView.findViewById(R.id.info_title);
                this.infoTime = itemView.findViewById(R.id.info_time);
                this.infoDesc = itemView.findViewById(R.id.info_desc);
                this.infoImage = itemView.findViewById(R.id.info_image);
                this.infoLogo = itemView.findViewById(R.id.info_logo);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_info, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position)
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
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    InfoDetailDialogFragment infoDetailDialogFragment = new InfoDetailDialogFragment();
                    assert getFragmentManager() != null;
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    infoDetailDialogFragment.show(ft, InfoDetailDialogFragment.TAG);
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
