package com.xy.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xy.model.Book;
import com.xy.util.UIUtil;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

public class LitepalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litepal);
        findViewById(R.id.createDatabaseByLitepalBtn).setOnClickListener(v -> {
            Connector.getDatabase();
            UIUtil.log("create database");
        });
        findViewById(R.id.addDatabaseByLitepalBtn).setOnClickListener(v -> {

            Book book = new Book();
            book.setName("The Da Vinci Code");
            book.setAuthor("Dan Brown");
            book.setPages(454);
            book.setPrice(16.96);
            book.setPress("Unkonwn");
            book.save();
            UIUtil.log("add data to database");
        });

        findViewById(R.id.updateDatabaseByLitepalBtn).setOnClickListener(v -> {
            Book book = new Book();
            book.setName("The Lost Symbol");
            book.setAuthor("Dan Brown");
            book.setPages(510);
            book.setPrice(19.95);
            book.setPress("Unkonwn");
            book.save();
            book.setPrice(10.99);
            book.save();
            UIUtil.log("update data to database");
        });

        findViewById(R.id.updateDatabaseByLitepalBtn2).setOnClickListener(v -> {
            Book book = new Book();
            book.setPrice(14.95);
            book.setAuthor("Author");
            book.setPress("Press");
            book.updateAll("name = ? and author=?", "The Lost Symbol", "Dan Brown");
            UIUtil.log("update data to database 2");

        });

        findViewById(R.id.deleteDatabaseByLitepalBtn).setOnClickListener(v -> {
            DataSupport.deleteAll(Book.class, "price < ?", "15");
        });
        findViewById(R.id.queryDatabaseByLitepalBtn).setOnClickListener(v -> {

            UIUtil.log(DataSupport.findAll(Book.class));
        });


    }
}
