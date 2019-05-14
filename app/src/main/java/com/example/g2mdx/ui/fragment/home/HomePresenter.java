package com.example.g2mdx.ui.fragment.home;

import com.example.g2mdx.R;
import com.example.g2mdx.data.network.model.HomeItem;
import com.example.g2mdx.data.prefs.PrefsHandler;
import com.example.g2mdx.data.prefs.PrefsInteractor;
import com.example.g2mdx.data.prefs.PrefsManager;
import com.example.g2mdx.ui.fragment.home.adapter.HomeAdapter;
import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter {

    private HomeView view;

    private List<HomeItem> items = new ArrayList<>();
    private PrefsInteractor prefsInteractor;

    HomePresenter(HomeView view, PrefsManager prefsManager) {
        this.view = view;
        prefsInteractor = new PrefsInteractor(prefsManager);
    }

    void addItems() {
        items.add(new HomeItem("Facebook Login", R.id.home_to_login));
        items.add(new HomeItem("SQL Lite", R.id.home_to_database));
        items.add(new HomeItem("Search Countries", R.id.home_to_search));
        items.add(new HomeItem("Phone Contacts", R.id.home_to_contacts));
        items.add(new HomeItem("Google Maps", R.id.home_to_maps));
        items.add(new HomeItem("Phone Calender", R.id.home_to_calender));
    }

    public int getItemsCount() {
        return items.size();
    }

    public void onBindItem(HomeAdapter.HomeHolder holder, int position) {
        HomeItem item = items.get(position);

        holder.setButtonText(item.getTitle());
    }

    public void onItemClick(int position) {
        HomeItem item = items.get(position);

        if (position == 0) {
            checkAccessToken();
        } else {
            view.navigateFragment(item.getAction());
        }
    }

    private void checkAccessToken() {
        prefsInteractor.getAccessToken(result -> {
            if (result == null) {
                view.navigateFragment(items.get(0).getAction());
            } else {
                view.startProfileFragment(result);
            }
        });
    }

}
