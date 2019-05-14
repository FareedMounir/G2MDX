package com.example.g2mdx.data.prefs;

import android.content.SharedPreferences;

import com.facebook.AccessToken;
import com.google.gson.Gson;

public class PrefsManager {

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public PrefsManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        gson = new Gson();
    }

    void setAccessToken(AccessToken accessToken) {
        String accessTokenString = gson.toJson(accessToken);
        sharedPreferences.edit().putString("accessToken", accessTokenString).apply();
    }

    AccessToken getAccessToken() {
        String accessTokenString = sharedPreferences.getString("accessToken", "");
        return gson.fromJson(accessTokenString, AccessToken.class);
    }

}
