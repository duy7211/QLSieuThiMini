package com.example.qlsieuthimini.nhanvien;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlsieuthimini.DatabaseHelper;
import com.example.qlsieuthimini.R;
import com.example.qlsieuthimini.Sanpham.BitmapUtility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Gio_hang extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    ImageView imgsanpham;
    ImageView imgMinus,imgPlus;
    Button btnsell;
    TextView tvNumber,tvTensanpham,tvGiasp,tvslkho,tvmotaSP;
    int number = 1;
    int ID;
    Bitmap bitmap;
    String slSanpham;
    String gia;
    int TongGia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        init();
        act();
        getData();
        saveData();
    }

    private void saveData() {
        btnsell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sl_spMua = tvNumber.getText().toString();
                String gia = tvGiasp.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String datetime = sdf.format(new Date());

                ContentValues values = new ContentValues();
                values.put("ID_SANPHAM",ID);
                values.put("SOLUONG",sl_spMua);
                values.put("THANHTIEN",gia);
                values.put("THOIGIAN",datetime);
                long r = db.insert("giohang",null,values);
                if (r == -1){
                    Toast.makeText(Gio_hang.this, "Bán thất bại", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Gio_hang.this, "Đã bán thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }


    private void getData() {
        Intent i = this.getIntent();
        ID = i.getIntExtra("ID",-1);
        if(ID == -1){
            finish();
        } else {
            Cursor cursor = db.rawQuery("SELECT * FROM sanpham WHERE ID=?", new String[]{ID+""});
            cursor.moveToFirst();
            tvTensanpham.setText("Tên: "+cursor.getString(1));
            tvslkho.setText("Số lượng: "+cursor.getString(2));
            tvmotaSP.setText(cursor.getString(3));
            bitmap = BitmapUtility.getImage(cursor.getBlob(4));
            imgsanpham.setImageBitmap(bitmap);
            tvGiasp.setText(cursor.getString(5));
            slSanpham = cursor.getString(2);
            gia = cursor.getString(5);
        }

    }

    private void act() {
        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number < Integer.valueOf(slSanpham)){
                    ++number;
                    tvNumber.setText(String.valueOf(number));
                    String soluong = tvNumber.getText().toString();

                    TongGia = 0;
                    if(TongGia == 0){
                        TongGia = Integer.valueOf(gia) * Integer.valueOf(soluong);
                        tvGiasp.setText(String.valueOf(TongGia));
                    }

                }
            }
        });

        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number > 1 ){
                    --number;
                    tvNumber.setText(String.valueOf(number));
                    String soluong = tvNumber.getText().toString();

                    TongGia = 0;
                    if(TongGia == 0){
                        TongGia = Integer.valueOf(gia) * Integer.valueOf(soluong);
                        tvGiasp.setText(String.valueOf(TongGia));
                    }


                }
            }
        });

        btnsell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void init() {
        imgMinus = findViewById(R.id.imgMinus);
        imgPlus = findViewById(R.id.imgPlus);
        tvNumber = findViewById(R.id.tvnumber);
        tvTensanpham = findViewById(R.id.tvtensanpham);
        tvGiasp = findViewById(R.id.tvgiasp);
        tvslkho = findViewById(R.id.tvsoluongkho);
        imgsanpham = findViewById(R.id.imgsanpham);
        tvmotaSP = findViewById(R.id.tvmotaSP);
        btnsell = findViewById(R.id.btnsell);
    }
}
