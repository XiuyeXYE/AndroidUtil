package com.xy.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataUtil {

    @FunctionalInterface
    public interface ConsumerForData<T> {
        void accept(T t) throws IOException;
    }

    public static void saveDataToFile(Context context, String filename, ConsumerForData<PrintWriter> consumer) {
        try (PrintWriter bw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE))))) {
            consumer.accept(bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendDataToFile(Context context, String filename, ConsumerForData<PrintWriter> consumer) {
        try (PrintWriter bw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_APPEND))))) {
            consumer.accept(bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readDataFromFile(Context context, String filename, ConsumerForData<BufferedReader> consumer) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput(filename)))) {
            consumer.accept(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveDataToSharedPreferences(Context context, String filename, ConsumerForData<SharedPreferences.Editor> consumer) {
        try {
            consumer.accept(context.getSharedPreferences(filename, Context.MODE_PRIVATE).edit());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readDataFromSharedPreferences(Context context, String filename, ConsumerForData<SharedPreferences> consumer) {
        try {
            consumer.accept(context.getSharedPreferences(filename, Context.MODE_PRIVATE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Retrofit retrofit(String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        return retrofit;
    }

    public static <T> Observable<T> resultWrapIO(Observable<T> result) {
        return result
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> T createRxInterface(String baseUrl, Class<T> clazz) {
        return retrofit(baseUrl).create(clazz);
    }


    public static String getDeviceID(Activity activity) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        int checkResult = activity.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE);
        if (checkResult == PackageManager.PERMISSION_GRANTED) {
            String imsi = mTelephonyMgr.getSubscriberId(); //获取IMSI号
            String imei = mTelephonyMgr.getDeviceId(); //获取IMEI号
            return imsi + imei;
        }
        return null;
    }

//    public static boolean requestDevicePermission(Activity activity, String[] permissions, int requestCode) {
//
//        activity.requestPermissions(permissions,requestCode);
//        return true;
//    }



}
