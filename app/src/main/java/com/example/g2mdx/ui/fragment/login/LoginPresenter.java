package com.example.g2mdx.ui.fragment.login;

import com.example.g2mdx.data.prefs.PrefsInteractor;
import com.example.g2mdx.data.prefs.PrefsManager;
import com.facebook.AccessToken;
import com.facebook.login.LoginResult;


class LoginPresenter {

    private LoginView view;
    private PrefsInteractor prefsInteractor;

    LoginPresenter(LoginView view, PrefsManager prefsManager) {
        this.view = view;
        prefsInteractor = new PrefsInteractor(prefsManager);
    }


    void onLoginSuccess(LoginResult loginResult, boolean rememberMe) {
        AccessToken accessToken = loginResult.getAccessToken();

        view.startProfileFragment(accessToken);

        if (rememberMe) {
            prefsInteractor.setAccessToken(accessToken);
        }
    }

}
