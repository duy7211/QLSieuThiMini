package com.example.qlsieuthimini;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qlsieuthimini.Sanpham.BitmapUtility;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class IUDActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Button btnExit, btnSave, btnDelete, btnimg;
    EditText edtTenSP, edtSL, edtMota,edtPrice;
    ImageView imgSP;
    String Action = null;
    int RQC = 111;
    Bitmap bitmap;
    int ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iud);
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();
        init();
        getReqAndData();
        act();
    }

    private void getReqAndData() {
        Intent i = this.getIntent();
        Action = i.getStringExtra("action");
        ID = i.getIntExtra("ID",-1);
        switch (Action) {
            case "update":
                ID = i.getIntExtra("ID",-1);
                if(ID == -1){
                    finish();
                } else {
                    Cursor cursor = db.rawQuery("SELECT * FROM sanpham WHERE ID=?", new String[]{ID+""});
                    cursor.moveToFirst();
                    edtTenSP.setText(cursor.getString(1));
                    edtSL.setText(cursor.getString(2));
                    edtMota.setText(cursor.getString(3));
                    bitmap = BitmapUtility.getImage(cursor.getBlob(4));
                    imgSP.setImageBitmap(bitmap);

                }
                break;
        }
    }

    private void act() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,RQC);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        String msg = "";
        String NAME = edtTenSP.getText().toString();
        String AMOUNT = edtSL.getText().toString();
        String DESCRIBE = edtMota.getText().toString();
        byte[] IMG = BitmapUtility.getBytes(bitmap);
        String PRICE = edtPrice.getText().toString();
        ContentValues values = new ContentValues();
        values.put("TENSP",NAME);
        values.put("SL",AMOUNT);
        values.put("MOTA",DESCRIBE);
        values.put("HINH",IMG);
        values.put("GIA",PRICE);

        switch (Action){
            case "add":
                if(!isEmptyData()){
                    msg = "Dữ liệu không hợp lệ";
                } else {
                    long r = db.insert("sanpham",null,values);
                    if (r == -1){
                        msg = "Thêm thất bại";
                    } else {
                        msg = "Thêm thành công";
                        clearControl();
                        finish();
                    }
                }
                break;
            case "update":
                int u = db.update("sanpham",values,"ID="+ID,null);
                if(u>0){
                    msg = "Cập nhật thành công";
                    finish();
                } else {
                    msg = "Cập nhật thất bại";
                }
                break;
        }
        if (!msg.isEmpty()){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmptyData() {
        String NAME = edtTenSP.getText().toString();
        String AMOUNT = edtSL.getText().toString();
        String Describe = edtMota.getText().toString();
        String PRICE = edtPrice.getText().toString();
        if(NAME.isEmpty())
            return false;
        if(AMOUNT.isEmpty())
            return false;
        if(Describe.isEmpty())
            return false;
        if(PRICE.isEmpty())
            return false;
        //if (imgSP.getDrawable() == null)
          //  return false;
        return true;
    }

    private void clearControl(){
        edtTenSP.getText().clear();
        edtSL.getText().clear();
        edtMota.getText().clear();
        imgSP.setImageBitmap(null);
        edtPrice.getText().clear();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RQC && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imgSP.setImageBitmap(bitmap);
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init() {
        btnExit = findViewById(R.id.btnExit);
        btnSave = findViewById(R.id.btnSave);
        btnimg = findViewById(R.id.btnImg);
        edtTenSP =findViewById(R.id.edtTenSP);
        edtSL = findViewById(R.id.edtSoluong);
        edtMota = findViewById(R.id.edtMota);
        imgSP = findViewById(R.id.imgSP);
        edtPrice = findViewById(R.id.edtPrice);
    }
}
