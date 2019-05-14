package com.example.g2mdx.ui.fragment.search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g2mdx.R;
import com.example.g2mdx.ui.fragment.search.SearchPresenter;

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private SearchPresenter presenter;

    public SearchAdapter(SearchPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        presenter.onBindItemView(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getCountriesCount();
    }
}
