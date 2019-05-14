package com.example.g2mdx.data.network;

import com.facebook.login.LoginResult;

public interface FacebookHandler {

    void onSuccess(LoginResult loginResult);

    void onError(String errorMessage);

}
