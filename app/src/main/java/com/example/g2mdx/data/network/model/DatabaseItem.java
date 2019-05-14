package com.example.g2mdx.data.network.model;

import com.example.g2mdx.ui.fragment.database.adapter.DatabaseListItem;

public class DatabaseItem implements DatabaseListItem {


    private String title;
    private String content;

    public DatabaseItem(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int getItemType() {
        return DatabaseListItem.TYPE_ITEM;
    }
}
