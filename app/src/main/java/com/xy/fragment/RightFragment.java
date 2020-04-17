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

public class RightFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        XLog.lg("RightFragment::onCreateView");
        return inflater.inflate(R.layout.right_layout, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XLog.lg("RightFragment::onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        XLog.lg("RightFragment::onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        XLog.lg("RightFragment::onStop");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        XLog.lg("RightFragment::onDestroy");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        XLog.lg("RightFragment::onAttach");
    }

    @Override
    public void onResume() {
        super.onResume();
        XLog.lg("RightFragment::onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        XLog.lg("RightFragment::onPause");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        XLog.lg("RightFragment::onActivityCreated");
    }
}

