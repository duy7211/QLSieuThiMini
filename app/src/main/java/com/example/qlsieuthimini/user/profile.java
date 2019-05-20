package com.example.qlsieuthimini.user;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.qlsieuthimini.DatabaseHelper;
import com.example.qlsieuthimini.R;
import com.example.qlsieuthimini.session.Session;

import java.util.HashMap;

public class profile extends AppCompatActivity {
    TextView tvHoten,tvNS,tvQue;
    Button btnEditInfo;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Session session;
    Cursor cursor;
    String idUserLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        session = new Session(this);
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        init();

        OpenActivityEdit();
    }

    @Override
    protected void onStart() {
        showUserProfile();
        super.onStart();
    }

    private void OpenActivityEdit() {
        btnEditInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(profile.this,IU_user.class);
                i.putExtra("ID",Integer.valueOf(idUserLogin));
                i.putExtra("action","update");
                startActivity(i);
            }
        });

    }

    private void showUserProfile() {
        HashMap<String,String> user = session.getUser();
        idUserLogin = user.get(session.Key_ID);
        cursor = db.rawQuery("SELECT * FROM user WHERE ID=?",new String[]{idUserLogin});
        cursor.moveToFirst();
        tvHoten.setText("Họ và tên: "+cursor.getString(3));
        tvQue.setText("Quê: "+cursor.getString(4));
        tvNS.setText("Năm sinh: "+cursor.getString(5));
        cursor.close();
    }

    private void init() {
        tvHoten = findViewById(R.id.tvvHoten);
        tvNS = findViewById(R.id.tvvNs);
        tvQue = findViewById(R.id.tvvQue);
        btnEditInfo = findViewById(R.id.btnEditInfo);
    }
}
