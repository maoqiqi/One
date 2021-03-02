package com.android.march.one.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class DividerDecoration extends RecyclerView.ItemDecoration {

    private static final int DEFAULT_COLOR = Color.parseColor("#FFDDDDDD");

    private int dividerHeight = 1;
    private int dividerColor;

    private Paint dividerPaint;

    public DividerDecoration() {
        this(1);
    }

    public DividerDecoration(int dividerHeight) {
        this(dividerHeight, DEFAULT_COLOR);
    }

    public DividerDecoration(int dividerHeight, int dividerColor) {
        this.dividerHeight = dividerHeight;
        this.dividerColor = dividerColor;

        if (this.dividerColor == 0)
            this.dividerColor = DEFAULT_COLOR;

        dividerPaint = new Paint();
        dividerPaint.setColor(this.dividerColor);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int top = view.getBottom();
            int bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }
}