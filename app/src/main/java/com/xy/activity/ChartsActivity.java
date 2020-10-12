package com.xy.activity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.xiuye.sharp.X;
import com.xiuye.util.cls.XType;

import java.util.List;

public class ChartsActivity extends AbstractBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        this.<LineChart>byId(R.id.lineChart).THEN(chart -> {
            //x axis,y axis
            List<Entry> data = XType.list();
            data.add(new Entry(0, 7));
            data.add(new Entry(1, 10));
            data.add(new Entry(2, 12));
            data.add(new Entry(3, 6));
            data.add(new Entry(4, 3));
            LineDataSet lineDataSet = new LineDataSet(data, "Chinese");
            LineData lineData = new LineData(lineDataSet);
//            lineData.addDataSet(lineDataSet);

            chart.setData(lineData);

            return X.DEFAULT_OBJECT;

        });
    }
}
