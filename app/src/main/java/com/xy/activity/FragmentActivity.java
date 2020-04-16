package com.xy.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xiuye.util.cls.XType;
import com.xy.fragment.AnotherRightFragment;
import com.xy.fragment.RightFragment;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);
        //只有动态的添加碎片，才能动态的替换？
        replaceFragment(XType.newInstance(RightFragment::new));
        findViewById(R.id.leftBtn).setOnClickListener(v -> {
            replaceFragment(new AnotherRightFragment());
        });
    }

    private int replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_fragment, fragment);
        transaction.addToBackStack(null);
        return transaction.commit();
    }
}
