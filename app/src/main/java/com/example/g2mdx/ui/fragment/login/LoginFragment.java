package com.example.g2mdx.ui.fragment.login;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.g2mdx.R;
import com.example.g2mdx.data.prefs.PrefsManager;
import com.example.g2mdx.utilities.Constants;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;


public class LoginFragment extends Fragment implements LoginView, FacebookCallback<LoginResult> {

    @BindView(R.id.facebookBTN)
    LoginButton loginButton;

    @BindView(R.id.rememberMeCB)
    CheckBox rememberMeCB;

    private LoginPresenter presenter;
    private CallbackManager callbackManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        initPresenter();
        initLoginButton();
    }

    private void initPresenter() {
        if (getContext() != null) {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences(Constants.PREFS_KEY, MODE_PRIVATE);
            presenter = new LoginPresenter(this, new PrefsManager(sharedPreferences));
        }
    }

    private void initLoginButton() {
        callbackManager = CallbackManager.Factory.create();
        loginButton.setFragment(this);
        loginButton.setPermissions(Arrays.asList("public_profile", "email"));
        loginButton.registerCallback(callbackManager, this);
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        presenter.onLoginSuccess(loginResult, rememberMeCB.isChecked());
    }

    @Override
    public void onCancel() {
        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(FacebookException error) {
        Toast.makeText(getContext(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void startProfileFragment(AccessToken accessToken) {
        if (getView() != null) {
            LoginFragmentDirections.LoginToProfile direction = LoginFragmentDirections.loginToProfile(accessToken);
            Navigation.findNavController(getView()).navigate(direction);
        }
    }
}
