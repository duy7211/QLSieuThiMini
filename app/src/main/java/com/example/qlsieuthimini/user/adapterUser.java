package com.example.qlsieuthimini.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qlsieuthimini.R;

import java.util.List;

public class adapterUser extends BaseAdapter {
    private Context context;
    private int layout;
    private List<clsUser> userList;

    public adapterUser(Context context, int layout, List<clsUser> userList) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);

        TextView tvUsername = convertView.findViewById(R.id.tvUsername);
        TextView tvHoten = convertView.findViewById(R.id.tvvHoten);
        TextView tvQue = convertView.findViewById(R.id.tvQueofUser);
        TextView tvNS = convertView.findViewById(R.id.tvQueofUser);

        clsUser user = userList.get(position);
        tvUsername.setText("Tài khoảng: "+user.getName());
        tvHoten.setText("Họ tên: "+user.getHoten());
        tvQue.setText("Quê: "+user.getQue());
        tvNS.setText("Năm Sinh: "+user.getNS());
        return convertView;
    }
}
