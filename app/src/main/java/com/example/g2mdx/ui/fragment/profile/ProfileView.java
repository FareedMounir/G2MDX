package com.example.g2mdx.ui.fragment.profile;

import android.net.Uri;

import com.example.g2mdx.data.network.model.User;

public interface ProfileView {

    void setProfileData(User user);

    void startFacebookShare(Uri uri);

}
