package com.android.march.one;

import android.util.Log;

import com.android.march.one.interfaces.ILoading;

import rx.Observer;

public abstract class MyObserver<T> implements Observer<T> {

    @Override
    public void onError(Throwable e) {
        Log.e("info", e.getMessage());
    }

    @Override
    public void onNext(T t) {
        onData(t);
    }

    @Override
    public void onCompleted() {
        Log.e("info", "onCompleted()");
    }

    protected abstract void onData(T t);
}