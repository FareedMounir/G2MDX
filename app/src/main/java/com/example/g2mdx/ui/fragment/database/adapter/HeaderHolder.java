package com.example.g2mdx.ui.fragment.database.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g2mdx.R;
import com.example.g2mdx.ui.fragment.database.DatabasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HeaderHolder extends RecyclerView.ViewHolder implements HeaderItemView {

    @BindView(R.id.titleET)
    EditText titleET;

    @BindView(R.id.contentET)
    EditText contentET;

    @BindView(R.id.headerBTN)
    Button headerBTN;

    private DatabasePresenter presenter;

    public HeaderHolder(@NonNull View itemView, DatabasePresenter presenter) {
        super(itemView);
        this.presenter = presenter;
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.headerBTN)
    void addItem() {
        String title = titleET.getText().toString();
        String content = contentET.getText().toString();

        presenter.addNewItem(this, title, content);
    }


    @Override
    public void setTitleError(String error) {
        titleET.setError(error);
    }

    @Override
    public void setContentError(String error) {
        contentET.setError(error);
    }
}
