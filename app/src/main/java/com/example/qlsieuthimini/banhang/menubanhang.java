package com.example.qlsieuthimini.banhang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.qlsieuthimini.R;
import com.example.qlsieuthimini.session.Session;
import com.example.qlsieuthimini.thanhtien.sale_history;
import com.example.qlsieuthimini.user.profile;

public class menubanhang extends AppCompatActivity {
    ImageView imgsanpham, imglgout, imgpf, imghistory;
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

        imgsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menubanhang.this,listsanpham.class));
            }
        });

        imgpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menubanhang.this, profile.class));
            }
        });

        imghistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menubanhang.this, sale_history.class));
            }
        });
    }

    private void init() {
        imgsanpham = findViewById(R.id.imgsanpham);
        imglgout = findViewById(R.id.imglgout);
        imgpf = findViewById(R.id.imgpf);
        imghistory = findViewById(R.id.imghistory);
    }
}
