package com.xy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.xy.lifecycle.MainActivityLifecycle;
import com.xy.util.UIUtil;

public class MainActivity extends AppCompatActivity {

    private String[] data = {"Sunny", "Cloudy", "Few Clouds", "Partly Cloudy", "Overcast", "Windy", "Calm", "Light Breeze",
            "Moderate", "Fresh Breeze", "Strong Breeze", "High Wind", "Gale", "Strong Gale", "Storm", "Violent Storm", "Hurricane",
            "Tornado", "Tropical Storm", "Shower Rain", "Heavy Shower Rain", "Thundershower", "Heavy Thunderstorm", "Thundershower with hail",
            "Light Rain", "Moderate Rain", "Heavy Rain", "Extreme Rain", "Drizzle Rain", "Storm", "Heavy Storm", "Severe Storm", "Freezing Rain",
            "Light to moderate rain", "Moderate to heavy rain", "Heavy rain to storm", "Storm to heavy storm", "Heavy to severe storm",
            "Rain", "Light Snow", "Moderate Snow", "Heavy Snow", "Snowstorm", "Sleet", "Rain And Snow", "Shower Snow", "Snow Flurry",
            "Light to moderate snow", "Moderate to heavy snow", "Heavy snow to snowstorm", "Snow", "Mist", "Foggy", "Haze", "Sand", "Dust",
            "Duststorm", "Sandstorm", "Dense fog", "Strong fog", "Moderate haze", "Heavy haze", "Severe haze", "Heavy fog", "Extra heavy fog",
            "Hot", "Cold", "Unknown"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getLifecycle().addObserver(new MainActivityLifecycle());

        Button toDemo1ActivityBtn = this.findViewById(R.id.toDemo1ActivityBtn);
        toDemo1ActivityBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo1Activity.class);
            this.startActivity(intent);
        });
        ListView requestResults = this.findViewById(R.id.requestResults);
        requestResults.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));

        requestResults.setOnItemClickListener((parent, v, pos, id) -> {
            UIUtil.log(this, "item clicked", parent, v, pos, id);
        });

    }

}
