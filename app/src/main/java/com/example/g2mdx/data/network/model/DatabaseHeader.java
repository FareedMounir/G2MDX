package com.example.g2mdx.data.network.model;

import com.example.g2mdx.ui.fragment.database.adapter.DatabaseListItem;

public class DatabaseHeader implements DatabaseListItem {

    @Override
    public int getItemType() {
        return DatabaseListItem.TYPE_HEADER;
    }
}
