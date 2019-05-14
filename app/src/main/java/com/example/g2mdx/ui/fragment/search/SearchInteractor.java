package com.example.g2mdx.ui.fragment.search;

import com.example.g2mdx.data.network.ResponseHandler;
import com.example.g2mdx.data.network.model.Country;
import com.example.g2mdx.utilities.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchInteractor {

    void getCountries(ResponseHandler<List<Country>> responseHandler) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        SearchAPI api = retrofit.create(SearchAPI.class);
        api.getCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        responseHandler.onSuccess(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                responseHandler.onFailure(t.getLocalizedMessage());
            }
        });
    }

}
