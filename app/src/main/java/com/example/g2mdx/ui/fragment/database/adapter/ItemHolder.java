package com.example.g2mdx.ui.fragment.database.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g2mdx.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemHolder extends RecyclerView.ViewHolder implements DatabaseItemView {

    @BindView(R.id.titleTV)
    TextView titleTV;

    @BindView(R.id.contentTV)
    TextView contentTV;

    public ItemHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setTitle(String title) {
        titleTV.setText(title);
    }

    @Override
    public void setContent(String content) {
        contentTV.setText(content);
    }
}
