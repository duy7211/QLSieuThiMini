package com.example.qlsieuthimini;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qlsieuthimini.Sanpham.allSanPham;

public class MainActivity extends AppCompatActivity {
    EditText edtUsn, edtPw;
    Button btnLogin, btnRegt;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        db.addUser("duy","123456");
        init();
        act();
    }

    private void act() {
        btnRegt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Register.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsn.getText().toString();
                String password = edtPw.getText().toString();
                boolean isExist = db.checkUser(username,password);
                if(isExist){
                    Intent i = new Intent(MainActivity.this, allSanPham.class);
                    i.putExtra("username", username);
                    startActivity(i);
                }else {
                    edtPw.getText().clear();
                    Toast.makeText(MainActivity.this, "Đăng nhập không thành công. Tài khoảng hoặc maatk khẩu sai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        edtUsn = findViewById(R.id.edtUsn);
        edtPw = findViewById(R.id.edtPw);
        btnLogin = findViewById(R.id.btnOK);
        btnRegt = findViewById(R.id.btnRegt);
    }
}
