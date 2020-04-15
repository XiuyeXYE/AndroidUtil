package com.xy.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.xiuye.util.cls.XType;
import com.xy.model.Fruit;
import com.xy.model.FruitAdapter;
import com.xy.util.UIUtil;

import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private List<Fruit> fruitList;

    private void initFruits() {
        fruitList = XType.list();
        for (int i = 0; i < 30; i++) {
            fruitList.add(new Fruit("Lemon 酸爽青柠檬", R.drawable.lemon));
            fruitList.add(new Fruit("Mango 热带果王芒果", R.drawable.mango));
            fruitList.add(new Fruit("Kiwi 进口水果猕猴桃", R.drawable.kiwi));
            fruitList.add(new Fruit("Cherry 美味可口的樱桃", R.drawable.cherry));
            fruitList.add(new Fruit("Strawberry 美味可口的草莓", R.drawable.strawberry));
            fruitList.add(new Fruit("Durian 美味的榴莲", R.drawable.durian));
            fruitList.add(new Fruit("Grape 进口葡提", R.drawable.grape));
            fruitList.add(new Fruit("Pitaya 进口火龙果", R.drawable.pitaya));
            fruitList.add(new Fruit("Blueberry 蓝宝石蓝莓", R.drawable.blueberry));
            fruitList.add(new Fruit("Banana 热带水果香蕉", R.drawable.banana));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initFruits();
        ListView lv = findViewById(R.id.fruitListView);
        lv.setAdapter(new FruitAdapter(this, R.layout.fruit_item, fruitList));
        lv.setOnItemClickListener((parent, view, position, id) -> {
            UIUtil.log(parent, view, position, id);
        });
    }
}
