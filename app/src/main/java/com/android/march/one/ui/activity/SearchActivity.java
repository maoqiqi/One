package com.android.march.one.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.march.one.R;
import com.android.march.one.StatusBarUtils;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.ui.view.SearchView;

public class SearchActivity extends BaseActivity {

    private SearchView searchView;

    public static void start(Context context, int titleColor) {
        Bundle bundle = new Bundle();
        bundle.putInt("titleColor", titleColor);
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(searchView = new SearchView(this));
        StatusBarUtils.setStatusFullscreen(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            searchView.setData(bundle.getInt("titleColor", R.color.colorNavigation));
        }
    }

    @Override
    public void onBackPressed() {
        searchView.back();
    }
}