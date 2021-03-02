package com.android.march.one.ui.view;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;
import com.google.android.flexbox.FlexboxLayoutManager;

import butterknife.BindView;

public class SearchView extends RootView<BasePresenter> implements TextView.OnEditorActionListener {

    @BindView(R.id.view)
    View view;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.llSearchHistory)
    LinearLayout llSearchHistory;
    @BindView(R.id.tvSearchClean)
    TextView tvSearchClean;
    @BindView(R.id.recyclerViewSearchHistory)
    RecyclerView recyclerViewSearchHistory;
    @BindView(R.id.recyclerViewSearch)
    RecyclerView recyclerViewSearch;

    public SearchView(Context context) {
        super(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_search;
    }

    public void setData(int titleColor) {
        setToolBar(toolbar);
        view.setBackgroundResource(titleColor);
        toolbar.setBackgroundResource(titleColor);
        toolbar.setPopupTheme(R.style.ToolBarPopupTheme_News);

        editSearch.setOnEditorActionListener(this);

        recyclerViewSearchHistory.setLayoutManager(new FlexboxLayoutManager(getContext()));
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            // search();
        }
        return false;
    }
}