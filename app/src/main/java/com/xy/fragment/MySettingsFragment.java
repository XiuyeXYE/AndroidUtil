package com.xy.fragment;


import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.xy.activity.R;

public class MySettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
//        setPreferencesFromResource(R.xml.references, rootKey);
        setPreferencesFromResource(R.xml.references2, rootKey);
    }
}
