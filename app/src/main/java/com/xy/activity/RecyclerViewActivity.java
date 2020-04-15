package com.xy.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiuye.util.cls.XType;
import com.xy.model.Fruit;
import com.xy.util.UIUtil;

import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {


    private List<Fruit> initFruits() {

        List<Fruit> fruitList = XType.list();
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

        return fruitList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        RecyclerView rv = findViewById(R.id.recyclerView);
        LinearLayoutManager manger = new LinearLayoutManager(this);
        rv.setLayoutManager(manger);
        rv.setAdapter(new FruitAdpater(initFruits()));

    }

    static class FruitAdpater extends RecyclerView.Adapter<FruitAdpater.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
            ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(v -> {
                UIUtil.log("the whole view clicked:", parent, viewType, v);
            });
//            holder.fruitImage.setOnClickListener(v->{
//                UIUtil.log("image clicked:",v);
//            });
            return holder;

        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Fruit fruit = mFruitList.get(position);
            holder.fruitImage.setImageResource(fruit.getImageId());
            holder.fruitName.setText(fruit.getName());
        }

        @Override
        public int getItemCount() {
            return mFruitList.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView fruitImage;
            TextView fruitName;

            public ViewHolder(View view) {
                super(view);
                fruitImage = view.findViewById(R.id.fruit_image);
                fruitName = view.findViewById(R.id.fruit_name);
            }
        }

        private List<Fruit> mFruitList;

        public FruitAdpater(List<Fruit> fruitList) {
            this.mFruitList = fruitList;
        }

    }
}
