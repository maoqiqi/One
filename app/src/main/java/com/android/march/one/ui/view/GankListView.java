package com.android.march.one.ui.view;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.android.march.one.OneUtils;
import com.android.march.one.R;
import com.android.march.one.RecyclerViewScrollListener;
import com.android.march.one.RootView;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.adapter.TypeFactory;
import com.android.march.one.model.bean.ItemBean;
import com.android.march.one.presenter.contract.GankListContract;

import java.util.List;

import butterknife.BindView;

public class GankListView extends RootView<GankListContract.Presenter> implements GankListContract.View {

    private String category;
    private int pageIndex = 1;
    private int pageCount = 10;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public GankListView(Context context) {
        super(context);
    }

    public GankListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_gank_list;
    }

    public void setData(String category) {
        this.category = category;
        presenter.getData(this.category, pageIndex, pageCount);
    }

    @Override
    public void setData(List<ItemBean> list) {
        if (category.equals("福利")) {
            // 构造器中,第一个参数表示列数或者行数,第二个参数表示滑动方向,瀑布流
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
            recyclerView.setLayoutManager(manager);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new RecyclerViewAdapter<>(list, new TypeFactory().setWelfareData(activity)));
            recyclerView.addOnScrollListener(new RecyclerViewScrollListener(getContext()));
        } else {
            OneUtils.setRecyclerView(getContext(), recyclerView);
            recyclerView.setAdapter(new RecyclerViewAdapter<>(list, new TypeFactory().setGankListData(category)));
        }
    }
}