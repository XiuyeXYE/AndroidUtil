package com.xy.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xiuye.util.cls.XType;
import com.xy.database.DatabaseHelper;
import com.xy.util.UIUtil;

public class SqliteActivity extends AppCompatActivity {

    DatabaseHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        dh = XType.newInstance(DatabaseHelper::new, this, "BookStore.db", null, 1);
        findViewById(R.id.createDBBtn).setOnClickListener(v -> {
            dh.getWritableDatabase();
            UIUtil.log("create database");
        });
        findViewById(R.id.addDataBtn).setOnClickListener(v -> {
            SQLiteDatabase db = dh.getWritableDatabase();
            ContentValues values = new ContentValues();
            for (int i = 0; i < 1000; i++) {
                values.put("name", "The Da Vinci Code " + i);
                values.put("author", "Dan Brown " + i);
                values.put("ages", 454 + i);
                values.put("price", 16.94 + i);
                db.insert("book", null, values);
                values.clear();
            }
            UIUtil.log("add data");
        });
        findViewById(R.id.updateDataBtn).setOnClickListener(v -> {
            SQLiteDatabase db = dh.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("price", 10.99);
            db.update("book", values, "name=?", new String[]{
                    "The Da Vinci Code 0"
            });
            UIUtil.log("update data");
        });
        findViewById(R.id.deleleDataBtn).setOnClickListener(v -> {
            SQLiteDatabase db = dh.getWritableDatabase();
            db.delete("book", "ages>?", new String[]{"500"});
            UIUtil.log("delete data");
        });
        findViewById(R.id.queryDataBtn).setOnClickListener(v -> {
            SQLiteDatabase db = dh.getWritableDatabase();
            Cursor cursor = db.query("book", null, null, null, null, null, null);
            StringBuffer str = new StringBuffer();
            if (cursor.moveToFirst()) {
                do {
                    str.append(cursor.getString(cursor.getColumnIndex("name")) + "|");
                    str.append(cursor.getString(cursor.getColumnIndex("author")) + "|");
                    str.append(cursor.getInt(cursor.getColumnIndex("ages")) + "|");
                    str.append(cursor.getDouble(cursor.getColumnIndex("price")) + "|");
                } while (cursor.moveToNext());
            }
            cursor.close();
            UIUtil.log("query data:", str);
        });
    }
}
