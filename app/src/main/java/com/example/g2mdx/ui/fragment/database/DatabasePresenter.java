package com.example.g2mdx.ui.fragment.database;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.g2mdx.R;
import com.example.g2mdx.data.network.model.DatabaseHeader;
import com.example.g2mdx.data.network.model.DatabaseItem;
import com.example.g2mdx.data.sql.DatabaseHandler;
import com.example.g2mdx.data.sql.DatabaseHelper;
import com.example.g2mdx.ui.fragment.database.adapter.DatabaseListItem;
import com.example.g2mdx.ui.fragment.database.adapter.HeaderHolder;
import com.example.g2mdx.ui.fragment.database.adapter.ItemHolder;

import java.util.ArrayList;
import java.util.List;

public class DatabasePresenter {

    private List<DatabaseListItem> databaseList = new ArrayList<>();
    private List<DatabaseItem> items = new ArrayList<>();

    private DatabaseView view;
    private DatabaseHelper itemsDB;

    DatabasePresenter(DatabaseView view, DatabaseHelper itemsDB) {
        this.view = view;
        this.itemsDB = itemsDB;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView;


        if (viewType == DatabaseListItem.TYPE_HEADER) {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_database_header, viewGroup, false);
            return new HeaderHolder(itemView, this);
        } else {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_database_item, viewGroup, false);
            return new ItemHolder(itemView);
        }
    }

    public int getListCount() {
        return databaseList.size();
    }

    public void onBindItemView(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHolder) {
            onBindItem((ItemHolder) holder, position);
        }
    }

    public void addNewItem(HeaderHolder holder, String title, String content) {
        if (isTextEmpty(title.trim())) {
            holder.setTitleError("Enter Title");
        } else {
            if (isTextEmpty(content)) {
                holder.setContentError("Enter Content");
            } else {
                saveItem(title, content);
            }
        }
    }

    private void saveItem(String title, String content) {
        itemsDB.addItem(title, content, new DatabaseHandler() {
            @Override
            public void onSuccess() {
                getItems();
            }

            @Override
            public void showDatabaseMessage(String message) {
                view.showMessage(message);
            }
        });
    }

     void getItems() {
         databaseList.clear();
         items.clear();

         databaseList.add(new DatabaseHeader());

         Cursor data = itemsDB.getItems();

         while (data.moveToNext()) {
            String title = data.getString(1);
            String content = data.getString(2);

            databaseList.add(new DatabaseItem(title, content));
            items.add(new DatabaseItem(title, content));
        }

        view.updateItems();
    }

    private boolean isTextEmpty(String text) {
        return text.isEmpty();
    }

    private void onBindItem(ItemHolder holder, int position) {
        DatabaseItem item = items.get(position - 1);

        holder.setTitle(item.getTitle());
        holder.setContent(item.getContent());
    }

    public int getItemViewType(int position) {
        return databaseList.get(position).getItemType();
    }

}
