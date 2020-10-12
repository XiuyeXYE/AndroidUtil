package com.xy.activity;

import android.os.Bundle;

import com.xiuye.sharp.X;
import com.yalantis.phoenix.PullToRefreshView;

public class PullToRefreshViewActivity extends AbstractBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_view);

        this.<PullToRefreshView>byId(R.id.pull_to_refresh).E(d -> {
            d.setOnRefreshListener(() -> {
                d.postDelayed(() -> {
                    d.setRefreshing(false);
                }, 1000);

            });
            return X.DEFAULT_OBJECT;
        });

    }
}