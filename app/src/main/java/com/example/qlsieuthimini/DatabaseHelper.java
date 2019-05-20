package com.example.qlsieuthimini;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QlSieuthi";
    private  static final String TABLE_USER = "user";
    private  static final String TABLE_SP = "sanpham";

    public static final String user = "CREATE TABLE IF NOT EXISTS "+TABLE_USER+" " +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "USER VARCHAR(100), "+
            "PASSWORD VARCHAR(100), " +
            "HOTEN VARCHAR(100), " +
            "QUE VARCHAR(100), " +
            "NAMSINH DATE," +
            "QUYEN INTEGER NOT NULL" +
            ")";
    public static final String sanpham = "CREATE TABLE IF NOT EXISTS "+TABLE_SP+" " +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "TENSP VARCHAR(100), "+
            "SL VARCHAR(100), "+
            "MOTA VARCHAR(100), "+
            "HINH BLOB," +
            "GIA VARCHAR(100)" +
            ")";
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

    public void addUser (String userName, String passWord,String Hoten,String Que,String NS, int quyen){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("USER", userName);
        values.put("PASSWORD", passWord);
        values.put("HOTEN",Hoten);
        values.put("QUE",Que);
        values.put("NAMSINH",NS);
        values.put("QUYEN",quyen);
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

   public int getIdUserByname(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_USER+" WHERE USER=?",new String[]{username});
        cursor.moveToFirst();
        int ID = cursor.getInt(0);
        cursor.close();
        db.close();
        return ID;
    }

    public int getPermissionByUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_USER+" WHERE USER=?",new String[]{username});
        cursor.moveToFirst();
        int quyen = cursor.getInt(6);
        cursor.close();
        db.close();
        return quyen;
    }
}
