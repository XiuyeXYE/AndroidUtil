package com.xy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xiuye.util.log.XLog;
import com.xy.activity.R;


public class AnotherRightFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        XLog.lg("AnotherRightFragment::onCreate");
        return inflater.inflate(R.layout.another_right_layout, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XLog.lg("AnotherRightFragment::onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        XLog.lg("AnotherRightFragment::onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        XLog.lg("AnotherRightFragment::onStop");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        XLog.lg("AnotherRightFragment::onDestroy");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        XLog.lg("AnotherRightFragment::onAttach");
    }

    @Override
    public void onResume() {
        super.onResume();
        XLog.lg("AnotherRightFragment::onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        XLog.lg("AnotherRightFragment::onPause");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        XLog.lg("AnotherRightFragment::onActivityCreated");
    }
}
