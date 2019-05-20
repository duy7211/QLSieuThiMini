package com.example.qlsieuthimini.Sanpham;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qlsieuthimini.DatabaseHelper;
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
    int ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_san_pham);
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        init();
        act();
    }

    @Override
    protected void onStart() {
        showData();
        super.onStart();
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
                        cursor.getBlob(4),
                        cursor.getString(5)
                ));
            }
        }
        adapterSP = new adapterSP(this, R.layout.row_sp, sanphamList);
        lvSP.setAdapter(adapterSP);

    }

    private void act() {
        //Thêm sản phẩm
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(allSanPham.this, IUsanpham.class);
                i.putExtra("action", "add");
                startActivity(i);
            }
        });
        //Lấy ID từ listView gửi qua cho IUsanpham để thực hiện update sp.
        lvSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             clsSanpham sp = sanphamList.get(position);
            //Toast.makeText(allSanPham.this,String.valueOf(sp.getID()), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(allSanPham.this, IUsanpham.class);
            i.putExtra("ID",sp.getID());
            i.putExtra("action","update");
            startActivity(i);
        }
        });
        //Xóa sản phẩm
        lvSP.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                clsSanpham sp = sanphamList.get(position);
                ID = sp.getID();
                //Toast.makeText(allSanPham.this, String.valueOf(ID), Toast.LENGTH_SHORT).show();
                deletesSP();
                return true;
            }
        });

    }

    private void deletesSP() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("Xóa sản phẩm");
        builder.setMessage("Bạn có muốn xóa sản phẩm này không?");
        //Sự kiện xác nhận
        builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int i = db.delete("sanpham","ID="+ID,null );
                if(i<1){
                    Toast.makeText(allSanPham.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(allSanPham.this, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                    showData();
                }
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void init() {
        btnAdd = findViewById(R.id.btnAdd);
        lvSP = findViewById(R.id.lvSP);
    }
    }
