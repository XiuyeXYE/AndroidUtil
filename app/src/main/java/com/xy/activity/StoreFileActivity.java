package com.xy.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xy.util.DataUtil;

public class StoreFileActivity extends AppCompatActivity {

    private static final String filename = "data.dat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_file);

        findViewById(R.id.saveDataToFileBtn).setOnClickListener(v -> {
            DataUtil.saveDataToFile(this, filename, w -> {
                EditText editor = findViewById(R.id.inputSaveDataToFileEditor);
                w.println(editor.getText().toString());
                w.flush();
            });
//            while (true);//executed at ui main thread!
        });

        findViewById(R.id.readDataFromFileBtn).setOnClickListener(v -> {
            DataUtil.readDataFromFile(this, filename, r -> {
                TextView tv = findViewById(R.id.outputReadDataFromFileText);
                StringBuffer content = new StringBuffer();
                String readStr = null;
                while ((readStr = r.readLine()) != null) {
                    content.append(readStr);
                }
                tv.setText(content.toString());

            });
        });

    }
}
