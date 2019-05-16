package com.example.qlsieuthimini;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QlSieuthi";
    private  static final String TABLE_USER = "user";
    private  static final String TABLE_SP = "sanpham";

    public static final String user = "CREATE TABLE IF NOT EXISTS "+TABLE_USER+" " +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "USER VARCHAR(100), "+
            "PASSWORD VARCHAR(100))";
    public static final String sanpham = "CREATE TABLE IF NOT EXISTS "+TABLE_SP+" " +
            "(ID INTERGER PRIMARY KEY AUTOINCREMENT, "+
            "TENSP VARCHAR(100), "+
            "SL VARCHAR(100), "+
            "MOTA VARCHAR(100), "+
            "HINH BLOB)";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(user);
        db.execSQL(sanpham);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SP);
        onCreate(db);
    }

    public void addUser (String userName, String passWord){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("USER", userName);
        values.put("PASSWORD", passWord);
        long r =  db.insert(TABLE_USER,null,values);
        db.close();
    }

    public boolean checkUserExists(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM "+TABLE_USER+" WHERE USER=?", new String[] {username});
        int cout = cursor.getCount();
        cursor.close();
        db.close();

        if(cout > 0 ){
            return true;
        } else return false;
    }

    public boolean checkUser(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_USER+" WHERE USER=? AND PASSWORD=?",new String[] {username,password});
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if(count > 0){
            return true;
        } else {
            return false;
        }
    }
}
