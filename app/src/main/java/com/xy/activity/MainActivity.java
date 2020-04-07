package com.xy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.xiuye.util.cls.XType;
import com.xiuye.util.log.XLog;
import com.xy.lifecycle.MainActivityLifecycle;
import com.xy.util.UIUtil;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

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

        RadioGroup sexRadioGroup = findViewById(R.id.sexRadioGroup);

        findViewById(R.id.fire).setOnClickListener(v -> {
            int checkedButtonId = sexRadioGroup.getCheckedRadioButtonId();
            if (checkedButtonId == -1) {
                UIUtil.log(this, "please select!");

            } else {
                RadioButton checkedButton = findViewById(checkedButtonId);
                String sex = checkedButton.getText().toString();
                //http://myptcc.com/Api/Myptcc/setUserInfo
                // ?
                // value=1&user_id=793&field=sex
                int sexId = 1;//man
//                if("男".equals(sex)){
//
//                }else
                if ("女".equals(sex)) {
                    sexId = 0;
                }

                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient.Builder client = new OkHttpClient.Builder();
                client.addInterceptor(interceptor);


                Retrofit retrofit = new Retrofit.Builder()
                        .client(client.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl("http://myptcc.com")
                        .build();
                RequestService rs = retrofit.create(RequestService.class);
                Map<String, Object> params = XType.map();
                params.put("value", sexId);
                params.put("user_id", 793);
                params.put("field", "sex");
                Observable<Map<String, Object>> result = rs.setUserIinfo(params);
                result
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(d -> {
                            XLog.log(d);
                            UIUtil.log(d.toString());
                            this.finish();
                        });


            }
        });

    }

}

interface RequestService {
    @GET("Api/Myptcc/setUserInfo")
    Observable<Map<String, Object>> setUserIinfo(@QueryMap Map<String, Object> params);

}