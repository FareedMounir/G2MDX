package com.example.g2mdx.ui.fragment.search;

import com.example.g2mdx.data.network.model.Country;
import com.example.g2mdx.utilities.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SearchAPI {

    @GET(Constants.API_VEERSION + Constants.COUNTRIES)
    Call<List<Country>> getCountries();

}
