package com.example.qlsieuthimini;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qlsieuthimini.menu.menu;
import com.example.qlsieuthimini.nhanvien.menubanhang;
import com.example.qlsieuthimini.session.Session;

import java.util.HashMap;

public class login extends AppCompatActivity {
    EditText edtUsn, edtPw;
    Button btnLogin;
    DatabaseHelper db;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        session = new Session(this);
        if(!db.checkUserExists("admin")){
            db.addUser("admin","123456",null,null,null,1);
        }
        checkLogin();
        init();
        act();

    }

    private void checkLogin() {
        if(session.isLogin()){
            HashMap<String,String> user = session.getUser();
            String permission = user.get(session.Key_PERMISSION);
            if(permission.equals("1")){
                Intent i = new Intent(login.this,menu.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
            if (permission.equals("0")){
                Intent i = new Intent(login.this, menubanhang.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        }
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
                    int quyen = db.getPermissionByUsername(username);
                    session.createSession(String.valueOf(ID),username,String.valueOf(quyen));
                    //Intent i = new Intent(login.this, menu.class);
                    //i.putExtra("username", username);
                    checkLogin();
                    //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //startActivity(i);
                    //finish();


                }else {
                    edtPw.getText().clear();
                    Toast.makeText(login.this, "Đăng nhập không thành công. Tài khoảng hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
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
