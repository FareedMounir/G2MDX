package com.example.g2mdx.ui.fragment.profile;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.g2mdx.R;
import com.example.g2mdx.data.network.model.User;
import com.facebook.AccessToken;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment implements ProfileView {

    @BindView(R.id.profileIV)
    ImageView profileIV;

    @BindView(R.id.nameTV)
    TextView nameTV;


    private ProfilePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        initPresenter();
        getArgs();
    }

    private void initPresenter() {
        presenter = new ProfilePresenter(this);
    }

    @OnClick(R.id.shareBTN)
    void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onResult(requestCode, resultCode, data);
    }

    private void getArgs() {
        Bundle bundle = getArguments();

        if (bundle != null) {
            AccessToken accessToken = bundle.getParcelable("accessToken");
            presenter.getProfileData(accessToken);
        }
    }

    @Override
    public void setProfileData(User user) {
        Picasso.get().load(user.getProfilePicture()).into(profileIV);
        nameTV.setText(user.getFirstName());
    }

    @Override
    public void startFacebookShare(Uri uri) {
        try {
            if (getContext() != null) {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                SharePhoto photo = new SharePhoto.Builder().setBitmap(bitmap).build();
                SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();

                ShareDialog shareDialog = new ShareDialog(this);
                shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
