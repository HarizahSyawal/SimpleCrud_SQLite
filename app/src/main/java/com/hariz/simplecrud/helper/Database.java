package com.hariz.simplecrud.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "UTM";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "CREATE TABLE students (id INTEGER PRIMARY KEY autoincrement, name TEXT NOT NULL, email TEXT NOT NULL, matric_num VARCHAR NOT NULL)";
                db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAll(){
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM students" ;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("name", cursor.getString(1));
                map.put("email", cursor.getString(2));
                map.put("matric_num", cursor.getString(3));
                list.add(map);
            } while (
                    cursor.moveToNext()
            );
        }
        cursor.close();
        return list;
    }
    public void insert(String name, String email, String matric_num){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "INSERT INTO students (name, email, matric_num) VALUES ('"+name+"','"+email+"','"+matric_num+"')";
        database.execSQL(QUERY);
    }

    public void update(int id, String name, String email, String matric_num){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "UPDATE students SET name = '"+name+"', email = '"+email+"', matric_num = '"+matric_num+"' WHERE id = "+id;
        database.execSQL(QUERY);
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "DELETE FROM students WHERE id = "+id;
        database.execSQL(QUERY);
    }
}
