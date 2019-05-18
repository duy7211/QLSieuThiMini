package com.example.qlsieuthimini.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.qlsieuthimini.R;
import com.example.qlsieuthimini.Sanpham.allSanPham;
import com.example.qlsieuthimini.user.user;

public class menu extends AppCompatActivity{
    ImageView imgsp, imgnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
        act();
    }

    private void act() {
        imgsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menu.this, allSanPham.class));
            }
        });
        imgnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menu.this, user.class));
            }
        });
    }

    private void init() {
        imgsp = findViewById(R.id.imgsp);
        imgnv = findViewById(R.id.imguser);
    }
}
