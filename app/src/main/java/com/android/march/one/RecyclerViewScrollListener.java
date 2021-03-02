package com.android.march.one;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public final class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    private Context context;
    private boolean isScrolling;

    public RecyclerViewScrollListener(Context context) {
        this.context = context;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        // 在滑动时停止加载图片,在滑动停止时开始加载图片。这里为了避免重复设置增加开销,设置了一个标志变量来做判断。
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (isScrolling == true)
                Glide.with(context).resumeRequests();
            isScrolling = false;
        } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
            isScrolling = true;
            Glide.with(context).pauseRequests();
        }
    }
}