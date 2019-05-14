package com.example.g2mdx.data.prefs;

import com.facebook.AccessToken;

public class PrefsInteractor {

    private PrefsManager prefsManager;

    public PrefsInteractor(PrefsManager prefsManager) {
        this.prefsManager = prefsManager;
    }

    public void setAccessToken(AccessToken accessToken) {
        prefsManager.setAccessToken(accessToken);
    }

    public void getAccessToken(PrefsHandler<AccessToken> prefsHandler) {
        prefsHandler.onGetData(prefsManager.getAccessToken());
    }

}
