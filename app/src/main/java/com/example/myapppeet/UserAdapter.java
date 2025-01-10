package com.example.myapppeet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    private Context context;
    private List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        super(context, 0, userList);
        this.context = context;
        this.userList = userList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        User user = getItem(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(user.getUsername());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}

