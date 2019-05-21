package com.example.qlsieuthimini.thanhtien;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.qlsieuthimini.DatabaseHelper;
import com.example.qlsieuthimini.R;

import java.util.ArrayList;

public class sale_history extends AppCompatActivity {
    ListView lvSales;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    ArrayList<clsSale_history> historyArrayList;
    adapterSales adapterSales;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_history);
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        init();
        showData();
    }


    private void showData(){
        historyArrayList = new ArrayList<>();
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
        lvSales.setAdapter(adapterSales);
    }

    private void init() {
        lvSales = findViewById(R.id.lvSales);
    }
}
