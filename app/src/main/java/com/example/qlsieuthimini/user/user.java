package com.example.qlsieuthimini.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qlsieuthimini.DatabaseHelper;
import com.example.qlsieuthimini.R;
import com.example.qlsieuthimini.session.Session;

import java.util.ArrayList;
import java.util.HashMap;

public class user extends AppCompatActivity {
    adapterUser adapterUser;
    Cursor cursor;
    ArrayList<clsUser> userArrayList;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    ListView lvUser;
    Button btnAddUser;
    int ID;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        session = new Session(this);
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        init();
        act();
    }

    @Override
    protected void onStart() {
        showData();
        super.onStart();
    }

    private void showData() {
        HashMap<String,String> user = session.getUser();
        String idUserLogin = user.get(session.Key_ID);
        userArrayList = new ArrayList<>();
        cursor = db.rawQuery("SELECT * FROM user WHERE ID!=?",new String[]{idUserLogin+""});
        if(cursor != null){
            while (cursor.moveToNext()){
                userArrayList.add(new clsUser(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6)
                ));
            }
        }
        adapterUser = new adapterUser(this,R.layout.row_user,userArrayList);
        lvUser.setAdapter(adapterUser);
    }

    private void act() {
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(user.this,IU_user.class);
                i.putExtra("action","add");
                startActivity(i);
            }
        });

        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clsUser user = userArrayList.get(position);
                Intent i = new Intent(user.this,IU_user.class);
                i.putExtra("ID",user.getID());
                i.putExtra("action","update");
                startActivity(i);
            }
        });

        lvUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                clsUser user = userArrayList.get(position);
                ID = user.getID();
                DeleteUser();
                return true;
            }
        });

    }

    private void DeleteUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("Xóa sản phẩm");
        builder.setMessage("Bạn có muốn xóa sản phẩm này không?");
        //Sự kiện xác nhận
        builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int i = db.delete("user","ID="+ID,null );
                if(i<1){
                    Toast.makeText(user.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(user.this, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                    showData();
                }
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void init() {
        lvUser = findViewById(R.id.lvUser);
        btnAddUser = findViewById(R.id.btnAddUser);
    }
}
