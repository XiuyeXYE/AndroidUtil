package com.xy.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import com.xy.util.UIUtil;

public class SearchableActivity extends AbstractBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
        findViewById(R.id.searchBtn).setOnClickListener(v -> {
            Intent it = new Intent(Intent.ACTION_SEARCH);
            to(it);
        });
    }

    @Override
    public boolean onSearchRequested() {
        Bundle data = new Bundle();
        data.putString("key", "your info");
        startSearch(null, true, data, false);
        return super.onSearchRequested();
    }

    private void doMySearch(String query) {
        UIUtil.log(this, "searched OK");
    }
}
