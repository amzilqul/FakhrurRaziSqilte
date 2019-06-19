package com.fakhrurRazi.SqlCrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final  String DATABASE_NAME = "biodatamu.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table biodata(no integer primary key,nama text null,tgl text null,jk text null, alamat text null);";
        Log.d("Data","onCreate: "+sql);
        db.execSQL(sql);
        sql = "INSERT INTO biodata(no,nama,tgl,jk,alamat) VALUES ('001','ramzil huda','21-02-1987','pria','Tanjung Pauh');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
