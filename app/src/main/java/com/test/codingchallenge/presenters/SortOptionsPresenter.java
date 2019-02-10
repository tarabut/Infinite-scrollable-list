package com.test.codingchallenge.presenters;

import com.test.codingchallenge.R;
import com.test.codingchallenge.contracts.SortOptionsContract;
import com.test.codingchallenge.util.Params;
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

    @Override
    public void parseSortSelection(int viewId) {

        String sortOption = null;
        switch (viewId) {
            case R.id.rb_sort_price_descending:
                sortOption = Params.SORT_PRICE_DESCENDING;
                break;
            case R.id.rb_sort_price_ascending:
                sortOption = Params.SORT_PRICE_ASCENDING;
                break;
            case R.id.rb_sort_beds_descending:
                sortOption = Params.SORT_BED_DESCENDING;
                break;
            case R.id.rb_sort_beds_ascending:
                sortOption = Params.SORT_BED_ASCENDING;
                break;
        }

        if (mView.get() != null)
            mView.get().showSortedList(sortOption);
    }
}
