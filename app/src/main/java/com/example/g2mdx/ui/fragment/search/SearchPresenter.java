package com.example.g2mdx.ui.fragment.search;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.g2mdx.R;
import com.example.g2mdx.data.network.ResponseHandler;
import com.example.g2mdx.data.network.model.Country;
import com.example.g2mdx.ui.fragment.search.adapter.SearchViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenter {

    private SearchInteractor interactor = new SearchInteractor();
    private SearchView view;

    private List<Country> countries = new ArrayList<>();
    private List<Country> searchCountries = new ArrayList<>();

    SearchPresenter(SearchView view) {
        this.view = view;
    }

    void getCountries() {
        interactor.getCountries(new ResponseHandler<List<Country>>() {
            @Override
            public void onSuccess(List<Country> result) {

                countries.addAll(result);
                searchCountries.addAll(result);

                view.setAdapter();
            }

            @Override
            public void onFailure(String errorMessage) {
                view.showErrorMessage(errorMessage);
            }
        });
    }

    void search(String searchQuery) {
        searchCountries.clear();

        if (searchQuery.isEmpty()) {
            searchCountries.addAll(countries);
        } else {
            for (int i = 0; i < countries.size(); i++) {
                if (countries.get(i).getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                    searchCountries.add(countries.get(i));
                    Log.i("country", countries.get(i).getName());
                }
                view.updateItems();
            }
        }


    }

    public int getCountriesCount() {
        return searchCountries.size();
    }

    public void onBindItemView(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchViewHolder) {
            onBindSearchItem((SearchViewHolder) holder, position);
        }
    }

    private void onBindSearchItem(SearchViewHolder holder, int position) {
        Country country = searchCountries.get(position);

        holder.setName(country.getName());
        holder.setCapital(country.getCapital());
        holder.setNative(country.getNativeName());
    }


}
