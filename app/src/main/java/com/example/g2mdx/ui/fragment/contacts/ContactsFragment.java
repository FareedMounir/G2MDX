package com.example.g2mdx.ui.fragment.contacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.g2mdx.R;
import com.example.g2mdx.ui.fragment.contacts.adapter.ContactsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactsFragment extends Fragment implements ContactsView {

    private ContactsPresenter presenter;

    @BindView(R.id.contactsRV)
    RecyclerView contactsRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        initPresenter();
        initRecyclerView();

        if (getContext() != null) {
            presenter.getContacts(getContext().getContentResolver());
        }
    }


    private void initPresenter() {
        presenter = new ContactsPresenter(this);
    }

    private void initRecyclerView() {
        if (getContext() != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());

            contactsRV.setLayoutManager(layoutManager);
            contactsRV.addItemDecoration(itemDecoration);
            contactsRV.setAdapter(new ContactsAdapter(presenter));
        }
    }

    @Override
    public void callNumber(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

}
