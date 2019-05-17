package com.example.qlsieuthimini.Sanpham;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlsieuthimini.R;

import java.util.List;

public class adapterSP extends BaseAdapter {
    private Context context;
    private int layout;
    private List<clsSanpham> sanphamList;

    public adapterSP(Context context, int layout, List<clsSanpham> sanphamList) {
        this.context = context;
        this.layout = layout;
        this.sanphamList = sanphamList;
    }

    @Override
    public int getCount() {
        return sanphamList.size();
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

        TextView tvTenSP = convertView.findViewById(R.id.tvTenSP);
        TextView tvSL = convertView.findViewById(R.id.tvSL);
        ImageView imgSP = convertView.findViewById(R.id.imgSP);
        //Button btnEdit = convertView.findViewById(R.id.btnEdit);
        clsSanpham sp = sanphamList.get(position);
        tvTenSP.setText(sp.getName());
        tvSL.setText("Số lượng: "+sp.getAmount());
        imgSP.setImageBitmap(BitmapUtility.getImage(sp.getImg()));
        return convertView;
    }
}
