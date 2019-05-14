package com.example.g2mdx.ui.fragment.home;

import com.facebook.AccessToken;

interface HomeView {

    void navigateFragment(int action);

    void startProfileFragment(AccessToken accessToken);

}
