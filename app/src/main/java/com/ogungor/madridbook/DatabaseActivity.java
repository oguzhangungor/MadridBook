package com.ogungor.madridbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DatabaseActivity extends SQLiteOpenHelper {

    static SQLiteDatabase database;

    public DatabaseActivity(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS madrids (id INTEGER PRIMARY KEY, name VARCHAR,position VARCHAR,uniformnumber VARCHAR, image BLOB)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void AddDatabaseActivity(Madrids madrids) {
        SQLiteDatabase database = this.getWritableDatabase();
        String sqlData = "INSERT INTO madrids(name,position,uniformnumber,image) values (?,?,?,?)";
        SQLiteStatement sqLiteStatement = database.compileStatement(sqlData);
        sqLiteStatement.bindString(1, madrids.getName());
        sqLiteStatement.bindString(2, madrids.getPosition());
        sqLiteStatement.bindString(3, madrids.getUniformNumber());
        sqLiteStatement.bindBlob(4, madrids.getImage());
        sqLiteStatement.execute();

    }
}
