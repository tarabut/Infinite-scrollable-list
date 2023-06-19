package com.test.codingchallenge.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.test.codingchallenge.R;
import com.test.codingchallenge.contracts.SortOptionsContract;
import com.test.codingchallenge.presenters.SortOptionsPresenter;
import com.test.codingchallenge.util.Params;

//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.Unbinder;

/**
 * Created for Coding Challenge Project of PF.
 */
public class SortOptionsFragment extends Fragment
        implements SortOptionsContract.View, CompoundButton.OnCheckedChangeListener {

//    // to unbind ButterKnife on view destroy
//    protected Unbinder mUnbinder = null;

    // Presenter of property fragment in MVP
    private SortOptionsPresenter mPresenter;

//    // view binding
//    @BindView(R.id.rb_sort_price_descending)
//    RadioButton rbPriceDescending;
//    @BindView(R.id.rb_sort_price_ascending)
//    RadioButton rbPriceAscending;
//    @BindView(R.id.rb_sort_beds_descending)
//    RadioButton rbBedsDescending;
//    @BindView(R.id.rb_sort_beds_ascending)
//    RadioButton rbBedsAscending;

    private OnOptionSelectionListener mOnOptionSelectionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sort_options, container, false);

//        // ButterKnife data binding
//        mUnbinder = ButterKnife.bind(this, rootView);

        // Initiate presenter right after view setup
        this.mPresenter = new SortOptionsPresenter(this);

//        rbPriceDescending.setOnCheckedChangeListener(this);
//        rbPriceAscending.setOnCheckedChangeListener(this);
//        rbBedsDescending.setOnCheckedChangeListener(this);
//        rbBedsAscending.setOnCheckedChangeListener(this);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

//        // Unbinding data view
//        if (mUnbinder != null)
//            mUnbinder.unbind();

        // releasing resources held by presenter
        if (mPresenter != null)
            mPresenter.onRelease();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        mPresenter.parseSortSelection(compoundButton.getId());
    }

    public void setOnOptionSelectionListener(OnOptionSelectionListener listener) {
        this.mOnOptionSelectionListener = listener;
    }

    @Override
    public void showSortedList(String sortOption) {

        if (mOnOptionSelectionListener != null)
            mOnOptionSelectionListener.onOptionSelected(sortOption);
    }

    public interface OnOptionSelectionListener {
        void onOptionSelected (String sortOption);
    }
}
