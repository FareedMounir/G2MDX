package com.example.g2mdx.ui.fragment.search;

import com.example.g2mdx.data.network.ResponseHandler;
import com.example.g2mdx.data.network.model.Country;
import com.example.g2mdx.utilities.Constants;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.TlsVersion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class SearchInteractor {

    void getCountries(ResponseHandler<List<Country>> responseHandler) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
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

    OkHttpClient getClient() {
        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_0)
                .allEnabledCipherSuites()
                .build();

        return new OkHttpClient.Builder()
                .connectionSpecs(Collections.singletonList(spec))
                .build();
    }

}
