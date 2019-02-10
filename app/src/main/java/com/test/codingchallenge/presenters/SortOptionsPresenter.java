package com.test.codingchallenge.presenters;

import com.test.codingchallenge.contracts.SortOptionsContract;
import com.test.codingchallenge.views.fragments.SortOptionsFragment;

import java.lang.ref.WeakReference;

/**
 * Created for Coding Challenge Project of PF.
 */
public class SortOptionsPresenter implements SortOptionsContract.Presenter {

    private WeakReference<SortOptionsContract.View> mView;

    public SortOptionsPresenter(SortOptionsContract.View view) {
        this.mView = new WeakReference<>(view);
    }

    public void onRelease() {
        this.mView = new WeakReference<>(null);
    }

}
