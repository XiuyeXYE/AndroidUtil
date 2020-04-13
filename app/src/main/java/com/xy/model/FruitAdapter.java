package com.xy.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xiuye.util.cls.XType;
import com.xy.activity.R;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;


    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view;
        //主要是重用 TextView ImageView 实例对象
        //每次遇到新的水果，就赋值新的名字！
        ViewHolder vh;
        if (convertView == null) {
            //clone 对象吧，不然每次只有一个实例！
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            vh = XType.newInstance(ViewHolder::new);
            vh.fruitImage = view.findViewById(R.id.fruit_image);
            vh.fruitName = view.findViewById(R.id.fruit_name);
            //提前把相关的存储器存放在view的包包里，以后好拿出来用！
            view.setTag(vh);
        } else {
            view = convertView;
            vh = XType.cast(view.getTag());
        }


        vh.fruitImage.setImageResource(fruit.getImageId());
        vh.fruitName.setText(fruit.getName());

        return view;
    }

    class ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
    }
}
