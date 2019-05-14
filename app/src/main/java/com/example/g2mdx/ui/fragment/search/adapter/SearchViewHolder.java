package com.example.g2mdx.ui.fragment.search.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g2mdx.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchViewHolder extends RecyclerView.ViewHolder implements SearchItemView {

    @BindView(R.id.nameTV)
    TextView nameTV;

    @BindView(R.id.capitalTV)
    TextView capitalTV;

    @BindView(R.id.nativeTV)
    TextView nativeTV;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setName(String name) {
        nameTV.setText(name);
    }

    @Override
    public void setCapital(String capital) {
        capitalTV.setText(capital);
    }

    @Override
    public void setNative(String nativeName) {
        nativeTV.setText(nativeName);
    }
}
