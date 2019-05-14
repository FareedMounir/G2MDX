package com.example.g2mdx.ui.fragment.home;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.g2mdx.R;
import com.example.g2mdx.data.prefs.PrefsManager;
import com.example.g2mdx.ui.fragment.home.adapter.HomeAdapter;
import com.example.g2mdx.utilities.Constants;
import com.facebook.AccessToken;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment implements HomeView {

    private HomePresenter presenter;

    @BindView(R.id.mainRV)
    RecyclerView mainRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        showPermissions();
        initPresenter();
        initRecyclerView();
        presenter.addItems();
    }

    private void showPermissions() {
        Dexter.withActivity(getActivity()).withPermissions(Manifest.permission.READ_CONTACTS,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", "com.example.g2mdx", null);
                intent.setData(uri);
                startActivity(intent);
            }
        }).check();
    }

    private void initPresenter() {
        if (getContext() != null) {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences(Constants.PREFS_KEY, MODE_PRIVATE);
            presenter = new HomePresenter(this, new PrefsManager(sharedPreferences));
        }
    }

    private void initRecyclerView() {
        mainRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mainRV.setAdapter(new HomeAdapter(presenter));
    }

    @Override
    public void navigateFragment(int action) {
        if (getView() != null) {
            Navigation.findNavController(getView()).navigate(action);
        }
    }

    @Override
    public void startProfileFragment(AccessToken accessToken) {
        if (getView() != null) {
            HomeFragmentDirections.HomeToProfile direction = HomeFragmentDirections.homeToProfile(accessToken);
            Navigation.findNavController(getView()).navigate(direction);
        }
    }

}
