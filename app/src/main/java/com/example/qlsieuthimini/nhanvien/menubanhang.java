package com.example.qlsieuthimini.nhanvien;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.qlsieuthimini.R;
import com.example.qlsieuthimini.session.Session;

public class menubanhang extends AppCompatActivity {
    ImageView imgsanpham, imglgout;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menubanhang);
        session = new Session(this);
        init();
        act();
    }

    private void act() {
        imglgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logout();
                finish();
            }
        });
    }

    private void init() {
        imgsanpham = findViewById(R.id.imgsanpham);
        imglgout = findViewById(R.id.imglgout);
    }
}
