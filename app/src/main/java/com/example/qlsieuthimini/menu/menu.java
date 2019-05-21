package com.example.qlsieuthimini.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.qlsieuthimini.R;
import com.example.qlsieuthimini.Sanpham.allSanPham;
import com.example.qlsieuthimini.session.Session;
import com.example.qlsieuthimini.user.profile;
import com.example.qlsieuthimini.user.user;

public class menu extends AppCompatActivity{
    ImageView imgsp, imgnv, imgLogout, imgprofile, imginfo, imgTotal;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        session = new Session(this);
        init();
        act();

    }



    private void act() {
       imgsp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(menu.this,allSanPham.class);
               startActivity(i);
           }
       });

       imgnv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                Intent i = new Intent(menu.this,user.class);
                startActivity(i);
           }
       });

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logout();
                finish();

            }
        });
        imgprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menu.this, profile.class));
            }
        });

        imginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menu.this,info.class));
            }
        });

        imgTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menu.this,Total.class));
            }
        });
    }

    private void init() {
        imgsp = findViewById(R.id.imgsp);
        imgnv = findViewById(R.id.imguser);
        imgLogout = findViewById(R.id.imgLogout);
        imgprofile = findViewById(R.id.imgprofile);
        imginfo = findViewById(R.id.imginfo);
        imgTotal = findViewById(R.id.imgTotal);
    }
}
