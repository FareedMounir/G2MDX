package com.example.g2mdx.ui.fragment.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g2mdx.R;
import com.example.g2mdx.ui.fragment.home.HomePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    private HomePresenter presenter;

    public HomeAdapter(HomePresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new HomeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        presenter.onBindItem(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getItemsCount();
    }

    public class HomeHolder extends RecyclerView.ViewHolder implements HomeItemView, View.OnClickListener {

        @BindView(R.id.homeItemBTN)
        Button homeItemBTN;

        HomeHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setButtonText(String text) {
            homeItemBTN.setText(text);
        }

        @Override
        public void onClick(View view) {
            presenter.onItemClick(getAdapterPosition());
        }

    }

}
