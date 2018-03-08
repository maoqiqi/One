package com.android.march.one.adapter;

import android.view.ViewGroup;

import java.util.List;

public interface ITypeFactory {

    <T> int getViewType(List<T> list, int position);

    RecyclerViewAdapter.ViewHolder createViewHolder(ViewGroup parent, int viewType);
}