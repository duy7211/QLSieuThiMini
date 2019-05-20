package com.example.qlsieuthimini.user;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.qlsieuthimini.DatabaseHelper;
import com.example.qlsieuthimini.R;

public class IU_user extends AppCompatActivity {
    RadioGroup radioQuyen;
    RadioButton radioAdmin,radioUser;
    EditText edtUsn, edtPw, edtHoten,edtQue,edtNS;
    Button btnOk, btnExit;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    String action = null;
    int ID;
    int Quyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iu_user);
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();

        init();
        getReqAndData();
        act();
    }

    private void getReqAndData() {
        Intent i = this.getIntent();
        action = i.getStringExtra("action");
        ID = i.getIntExtra("ID",-1);
        switch (action){
            case "update":
                if (ID == -1){
                    finish();
                } else {
                    Cursor cursor = db.rawQuery("SELECT * FROM USER WHERE ID=?",new String[]{ID+""});
                    cursor.moveToFirst();
                    edtUsn.setText(cursor.getString(1));
                    edtPw.setText(cursor.getString(2));
                    edtHoten.setText(cursor.getString(3));
                    edtQue.setText(cursor.getString(4));
                    edtNS.setText(cursor.getString(5));
                    int quyen = cursor.getInt(6);
                    if(quyen == 1){
                        radioQuyen.check(R.id.radioAdmin);
                    } else {
                        radioQuyen.check(R.id.radioUser);
                    }
                    if(quyen == 0){
                        for(int j = 0; j<radioQuyen.getChildCount();j++){
                            radioQuyen.getChildAt(j).setEnabled(false);
                        }
                    }
                    cursor.close();
                }
                break;
        }

    }

    private void act() {

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();

            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        radioQuyen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioAdmin)
                    Quyen = 1;
                if(checkedId == R.id.radioUser)
                    Quyen = 0;
            }
        });

    }


    private void saveData() {

        String userName = edtUsn.getText().toString();
        String passWord = edtPw.getText().toString();
        String hoten = edtHoten.getText().toString();
        String que = edtQue.getText().toString();
        String ns = edtNS.getText().toString();
        ContentValues values = new ContentValues();
        values.put("USER",userName);
        values.put("PASSWORD", passWord);
        values.put("HOTEN",hoten);
        values.put("QUE",que);
        values.put("NAMSINH",ns);
        values.put("QUYEN",Quyen);
        switch (action){
            case "add":
                if(userName.isEmpty() || passWord.isEmpty()){
                    Toast.makeText(IU_user.this, "Không được bỏ trống các ô trên", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isExistUser = databaseHelper.checkUserExists(userName);
                    if (isExistUser) {
                        Toast.makeText(IU_user.this, "Tài khoảng tồn tại", Toast.LENGTH_SHORT).show();
                    } else {
                        if(passWord.length() >= 6) {
                            databaseHelper.addUser(userName, passWord,hoten,que,ns,Quyen);
                            Toast.makeText(IU_user.this, "Tạo tài khoảng thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        } else{
                            Toast.makeText(IU_user.this, "mật khẩu phải từ 6 kí tự trở lên", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case "update":
                int u = db.update("USER",values,"ID="+ID,null);
                db.close();
                if(u>0){
                    Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private void init() {
        edtUsn = findViewById(R.id.edtUsn);
        edtPw = findViewById(R.id.edtPw);
        btnExit = findViewById(R.id.btnExit);
        btnOk = findViewById(R.id.btnOK);
        edtHoten = findViewById(R.id.edtHoten);
        edtNS = findViewById(R.id.edtNS);
        edtQue = findViewById(R.id.edtQue);
        radioQuyen = findViewById(R.id.radioQuyen);
        radioAdmin = findViewById(R.id.radioAdmin);
        radioUser = findViewById(R.id.radioUser);

    }
}
