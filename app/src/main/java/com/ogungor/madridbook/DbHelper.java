package com.ogungor.madridbook;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    static SQLiteDatabase database;
    static ArrayList<String> nameArray;
    static ArrayList<String> idArray;
    static ArrayAdapter arrayAdapter;

    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
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

    public void addDbPlayers(Players players) {
        database = this.getWritableDatabase();
        String sqlData = "INSERT INTO madrids(name,position,uniformnumber,image) values (?,?,?,?)";
        SQLiteStatement sqLiteStatement = database.compileStatement(sqlData);
        sqLiteStatement.bindString(1, players.getName());
        sqLiteStatement.bindString(2, players.getPosition());
        sqLiteStatement.bindString(3, players.getUniformNumber());
        sqLiteStatement.bindBlob(4, players.getImage());
        sqLiteStatement.execute();

    }

    public void getDbPlayers() {
        try {

            Cursor cursor = database.rawQuery("SELECT * FROM madrids", null);
            int nameIx = cursor.getColumnIndex("name");
            int idIx = cursor.getColumnIndex("id");

            while (cursor.moveToNext()) {
                nameArray.add(cursor.getString(nameIx));
                idArray.add(String.valueOf(cursor.getInt(idIx)));
            }
            arrayAdapter.notifyDataSetChanged();

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
