package com.example.qlsieuthimini.thanhtien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlsieuthimini.R;
import com.example.qlsieuthimini.Sanpham.BitmapUtility;

import java.util.List;

public class adapterSales extends BaseAdapter {
    private Context context;
    private int layout;
    private List<clsSale_history> sale_historyList;

    public adapterSales(Context context, int layout, List<clsSale_history> sale_historyList) {
        this.context = context;
        this.layout = layout;
        this.sale_historyList = sale_historyList;
    }

    @Override
    public int getCount() {
        return sale_historyList.size();
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

        TextView tvNameSp = convertView.findViewById(R.id.tvNameSp);
        TextView tvSL_SP = convertView.findViewById(R.id.tv_slsp);
        TextView tvThanhtien = convertView.findViewById(R.id.tvThanhtien);
        TextView tvTime = convertView.findViewById(R.id.tvTime);
        ImageView imgSale = convertView.findViewById(R.id.imgsale);

        clsSale_history history = sale_historyList.get(position);

        tvNameSp.setText("Tên:"+ history.getProductName());
        tvSL_SP.setText("Số lượng: "+history.getAmount());
        tvThanhtien.setText(history.getPrice());
        tvTime.setText("Vào lúc: "+history.getTime());
        imgSale.setImageBitmap(BitmapUtility.getImage(history.getImgSale()));

        return convertView;
    }
}
