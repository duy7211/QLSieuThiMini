package com.example.qlsieuthimini.banhang;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.qlsieuthimini.DatabaseHelper;
import com.example.qlsieuthimini.R;
import com.example.qlsieuthimini.Sanpham.clsSanpham;

import java.util.ArrayList;

public class listsanpham extends AppCompatActivity {
    ListView lvSanpham;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    ArrayList<clsSanpham> sanphamList;
    adapterSP_nv adapterSP;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listsanpham);
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        init();
        act();
    }

    @Override
    protected void onStart() {
        ShowData();
        super.onStart();
    }

    private void ShowData(){
        sanphamList = new ArrayList<>();
        cursor = db.rawQuery("SELECT * FROM sanpham",null);
        if(cursor != null){
            while (cursor.moveToNext()){
                sanphamList.add(new clsSanpham(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getBlob(4),
                        cursor.getString(5)
                ));
            }
        }
        adapterSP = new adapterSP_nv(this, R.layout.row_sp, sanphamList);
        lvSanpham.setAdapter(adapterSP);
    }

    private void act() {
        lvSanpham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clsSanpham sp = sanphamList.get(position);
                Intent i = new Intent(listsanpham.this,Gio_hang.class);
                i.putExtra("ID",sp.getID());
                startActivity(i);
            }
        });

    }

    private void init() {
        lvSanpham = findViewById(R.id.lvsanpham);
    }
}
