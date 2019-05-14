package com.example.g2mdx.ui.fragment.database.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g2mdx.ui.fragment.database.DatabasePresenter;

public class DatabaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private DatabasePresenter presenter;

    public DatabaseAdapter(DatabasePresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return presenter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        presenter.onBindItemView(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getListCount();
    }

    @Override
    public int getItemViewType(int position) {
        return presenter.getItemViewType(position);
    }
}
