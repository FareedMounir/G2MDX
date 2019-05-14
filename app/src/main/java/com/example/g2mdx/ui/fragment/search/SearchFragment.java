package com.example.g2mdx.ui.fragment.search;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.g2mdx.R;
import com.example.g2mdx.ui.fragment.search.adapter.SearchAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFragment extends Fragment implements SearchView {

    @BindView(R.id.searchRV)
    RecyclerView searchRV;

    @BindView(R.id.searchET)
    EditText searchET;

    @BindView(R.id.searchPB)
    ProgressBar searchPB;

    private SearchPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        initPresenter();
        initEditText();
        initRecyclerView();
        presenter.getCountries();
    }

    private void initEditText() {
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.search(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void updateItems() {
        if (searchRV.getAdapter() != null) {
            searchRV.getAdapter().notifyDataSetChanged();
        }
    }

    private void initPresenter() {
        presenter = new SearchPresenter(this);
    }

    private void initRecyclerView() {
        if (getContext() != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());

            searchRV.setLayoutManager(layoutManager);
            searchRV.addItemDecoration(itemDecoration);

            searchRV.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    @Override
    public void setAdapter() {
        searchPB.setVisibility(View.GONE);
        searchET.setVisibility(View.VISIBLE);
        searchRV.setAdapter(new SearchAdapter(presenter));
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }
}
