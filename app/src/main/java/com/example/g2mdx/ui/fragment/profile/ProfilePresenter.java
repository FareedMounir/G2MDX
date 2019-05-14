package com.example.g2mdx.ui.fragment.profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.annotation.Nullable;

import com.example.g2mdx.data.network.model.User;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;

import org.json.JSONObject;

class ProfilePresenter {

    private ProfileView view;

    ProfilePresenter(ProfileView view) {
        this.view = view;
    }

    void getProfileData(AccessToken accessToken) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, (object, response) -> getFacebookData(object));
        graphRequest.executeAsync();
    }

    private void getFacebookData(JSONObject object) {
        try {
            String profilePicture = "https://graph.facebook.com/" + object.getString("id") + "/picture?width=250&height=250";
            String name = object.getString("name");
            view.setProfileData(new User(profilePicture, name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void onResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null && requestCode == 100) {
            Uri filePath = data.getData();
            view.startFacebookShare(filePath);
        }
    }

}
