package com.example.qlsieuthimini;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qlsieuthimini.menu.menu;
import com.example.qlsieuthimini.session.Session;

public class MainActivity extends AppCompatActivity {
    EditText edtUsn, edtPw;
    Button btnLogin;
    DatabaseHelper db;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        session = new Session(this);
        if(!db.checkUserExists("admin")){
            db.addUser("admin","123456",null,null,null);
        }
        init();
        act();
    }

    private void act() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsn.getText().toString();
                String password = edtPw.getText().toString();
                boolean isExist = db.checkUser(username,password);
                if(isExist){
                    int ID = db.getIdUserByname(username);
                    session.createSession(String.valueOf(ID),username);
                    Intent i = new Intent(MainActivity.this, menu.class);
                    i.putExtra("username", username);
                    startActivity(i);
                    finish();

                }else {
                    edtPw.getText().clear();
                    Toast.makeText(MainActivity.this, "Đăng nhập không thành công. Tài khoảng hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        edtUsn = findViewById(R.id.edtUsn);
        edtPw = findViewById(R.id.edtPw);
        btnLogin = findViewById(R.id.btnOK);
    }
}
