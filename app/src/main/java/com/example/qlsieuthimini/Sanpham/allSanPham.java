package com.example.qlsieuthimini.Sanpham;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.qlsieuthimini.DatabaseHelper;
import com.example.qlsieuthimini.IUDActivity;
import com.example.qlsieuthimini.R;

import java.util.ArrayList;

public class allSanPham extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    ArrayList<clsSanpham> sanphamList;
    adapterSP adapterSP;
    Cursor cursor;
    Button btnAdd;
    ListView lvSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_san_pham);
        databaseHelper = new DatabaseHelper(this);
        init();
        act();
        showData();
    }

    private void showData() {
        sanphamList = new ArrayList<>();
        cursor = db.rawQuery("SELECT * FROM sanpham",null);
        if(cursor != null){
            while (cursor.moveToNext()){
                sanphamList.add(new clsSanpham(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getBlob(4)
                ));
            }
        }
        adapterSP = new adapterSP(this, R.layout.row_sp, sanphamList);
        lvSP.setAdapter(adapterSP);
    }

    private void act() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(allSanPham.this, IUDActivity.class);
                i.putExtra("action","add");
                startActivity(i);
            }
        });
    }

    private void init() {
        btnAdd = findViewById(R.id.btnAdd);
        lvSP = findViewById(R.id.lvSP);
    }
}
