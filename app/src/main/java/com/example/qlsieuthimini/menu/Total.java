package com.example.qlsieuthimini.menu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlsieuthimini.DatabaseHelper;
import com.example.qlsieuthimini.R;
import com.example.qlsieuthimini.thanhtien.adapterSales;
import com.example.qlsieuthimini.thanhtien.clsSale_history;

import java.util.ArrayList;
import java.util.List;

public class Total extends AppCompatActivity {
    ListView lvTotal;
    TextView tvTotal;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    ArrayList<clsSale_history> historyArrayList;
    adapterSales adapterSales;
    Cursor cursor;
    Button btnReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        historyArrayList = new ArrayList<>();
        init();
        showData();
        Total();
        ResetTotal();
    }

    private void ResetTotal() {
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = db.delete("giohang",null,null);
                if(i<1){
                    Toast.makeText(Total.this, "reset thất bại", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Total.this, "reset Thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }
        });
    }

    private void showData(){
        cursor = db.rawQuery("SELECT giohang.ID,giohang.SOLUONG,giohang.THANHTIEN,giohang.THOIGIAN,sanpham.TENSP,sanpham.HINH " +
                "FROM giohang,sanpham WHERE giohang.ID_SANPHAM = sanpham.ID", null);
        if(cursor != null){
            while (cursor.moveToNext()){
                historyArrayList.add(new clsSale_history(
                        cursor.getInt(0),
                        cursor.getString(4),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getBlob(5)
                ));
            }
        }

        adapterSales = new adapterSales(this,R.layout.row_sale,historyArrayList);
        lvTotal.setAdapter(adapterSales);
        adapterSales.notifyDataSetChanged();
    }

    private void Total() {
        int sum = 0;
        clsSale_history saleHistory;
        for(int i = 0; i < historyArrayList.size();i++){
            saleHistory = historyArrayList.get(i);
            sum = sum +Integer.valueOf(saleHistory.getPrice());
        }
        tvTotal.setText(sum+" VNĐ");
    }

    private void init() {
        lvTotal = findViewById(R.id.lvTotal);
        tvTotal = findViewById(R.id.tvTotal);
        btnReset = findViewById(R.id.btnReset);
    }
}
