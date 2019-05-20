package com.example.qlsieuthimini.nhanvien;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlsieuthimini.DatabaseHelper;
import com.example.qlsieuthimini.R;
import com.example.qlsieuthimini.Sanpham.BitmapUtility;

public class Gio_hang extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    ImageView imgsanpham;
    ImageView imgMinus,imgPlus;
    TextView tvNumber,tvTensanpham,tvGiasp,tvslkho,tvmotaSP;
    int number = 1;
    int ID;
    Bitmap bitmap;
    String slSanpham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        init();
        act();
        getData();
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
            tvGiasp.setText("Giá: "+cursor.getString(5));
            slSanpham = cursor.getString(2);
        }

    }

    private void act() {
        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number < Integer.valueOf(slSanpham)){
                    number+=1;
                    tvNumber.setText(String.valueOf(number));

                }
            }
        });

        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number > 1 ){
                    number-=1;
                    tvNumber.setText(String.valueOf(number));
                }
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
    }
}
