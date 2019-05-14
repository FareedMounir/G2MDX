package com.example.g2mdx.ui.fragment.database;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.g2mdx.R;
import com.example.g2mdx.data.sql.DatabaseHelper;
import com.example.g2mdx.ui.fragment.database.adapter.DatabaseAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DatabaseFragment extends Fragment implements DatabaseView {

    private DatabasePresenter presenter;

    @BindView(R.id.databaseRV)
    RecyclerView databaseRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_database, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initPresenter();
        initRecyclerView();

        presenter.getItems();
    }

    private void initRecyclerView() {
        if (getContext() != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());

            databaseRV.setLayoutManager(layoutManager);
            databaseRV.addItemDecoration(itemDecoration);
            databaseRV.setAdapter(new DatabaseAdapter(presenter));
        }
    }

    private void initPresenter() {
        presenter = new DatabasePresenter(this, new DatabaseHelper(getContext()));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateItems() {
        if (databaseRV.getAdapter() != null) {
            databaseRV.getAdapter().notifyDataSetChanged();
        }
    }
}
