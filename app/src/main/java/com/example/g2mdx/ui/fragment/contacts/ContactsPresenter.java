package com.example.g2mdx.ui.fragment.contacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.g2mdx.data.network.model.Contact;
import com.example.g2mdx.ui.fragment.contacts.adapter.ContactsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContactsPresenter {

    private ContactsView view;
    private List<Contact> contacts = new ArrayList<>();

    private String lastName = "";
    private String lastNumber = "";

    ContactsPresenter(ContactsView view) {
        this.view = view;
    }

    public int getContactsCount() {
        return contacts.size();
    }

    void getContacts(ContentResolver contentResolver) {
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                while (cursor.moveToNext()) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                    if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                        Cursor contactsCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                        if (contactsCursor != null) {


                            while (contactsCursor.moveToNext()) {
                                String displayName = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
                                String phoneNumber = ContactsContract.CommonDataKinds.Phone.NUMBER;

                                int column = contactsCursor.getColumnIndex(displayName);
                                String name = contactsCursor.getString(column);
                                column = contactsCursor.getColumnIndex(phoneNumber);
                                String number = contactsCursor.getString(column).replace(" ", "");

                                isAddedBefore(name, number);
                            }

                            contactsCursor.close();
                        }
                    }
                }
                cursor.close();
            }
        }
    }

    public void callNumber(int position) {
        view.callNumber(contacts.get(position).getNumber());
    }

    private void isAddedBefore(String name, String number) {
        if (!name.equals(lastName) && !number.equals(lastNumber)) {
            contacts.add(new Contact(name, number));
        }

        lastName = name;
        lastNumber = number;
    }

    public void onBindItemView(ContactsAdapter.ContactsHolder holder, int position) {
        Contact contact = contacts.get(position);

        holder.setName(contact.getName());
        holder.setNumber(contact.getNumber());
    }

}
