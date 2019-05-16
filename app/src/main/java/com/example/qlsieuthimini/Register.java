package com.example.qlsieuthimini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText edtUsn, edtPw;
    Button btnOk, btnExit;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
        init();
        act();
    }

    private void act() {

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUsn.getText().toString();
                String passWord = edtPw.getText().toString();
                if(userName.isEmpty() || passWord.isEmpty()){
                    Toast.makeText(Register.this, "Không được bỏ trống các ô trên", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isExistUser = db.checkUserExists(userName);
                    if (isExistUser) {
                        Toast.makeText(Register.this, "Tài khoảng tồn tại", Toast.LENGTH_SHORT).show();
                    } else {
                        if(passWord.length() >= 6) {
                            db.addUser(userName, passWord);
                            Toast.makeText(Register.this, "Tạo tài khoảng thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        } else{
                            Toast.makeText(Register.this, "mật khẩu phải từ 6 kí tự trở lên", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void init() {
        edtUsn = findViewById(R.id.edtUsn);
        edtPw = findViewById(R.id.edtPw);
        btnExit = findViewById(R.id.btnExit);
        btnOk = findViewById(R.id.btnOK);
    }
}
