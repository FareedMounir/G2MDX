package com.example.g2mdx.ui.fragment.contacts.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g2mdx.R;
import com.example.g2mdx.ui.fragment.contacts.ContactsPresenter;
import com.example.g2mdx.ui.fragment.contacts.ContactsView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsHolder> {

    private ContactsPresenter presenter;

    public ContactsAdapter(ContactsPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ContactsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsHolder holder, int position) {
        presenter.onBindItemView(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getContactsCount();
    }

    public class ContactsHolder extends RecyclerView.ViewHolder implements ContactsItemView ,View.OnClickListener{

        @BindView(R.id.nameTV)
        TextView nameTV;

        @BindView(R.id.numberTV)
        TextView numberTV;


        ContactsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void setName(String name) {
            nameTV.setText(name);
        }

        @Override
        public void setNumber(String number) {
            numberTV.setText(number);
        }

        @Override
        public void onClick(View v) {
            presenter.callNumber(getAdapterPosition());
        }
    }

}
