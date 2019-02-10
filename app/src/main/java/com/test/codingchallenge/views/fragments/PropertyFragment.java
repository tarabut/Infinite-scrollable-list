package com.test.codingchallenge.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.test.codingchallenge.R;
import com.test.codingchallenge.contracts.PropertyContract;
import com.test.codingchallenge.models.search.PropertyResource;
import com.test.codingchallenge.presenters.PropertyPresenter;
import com.test.codingchallenge.util.Params;
import com.test.codingchallenge.util.SnackBarUtil;
import com.test.codingchallenge.views.adapters.PropertyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created for Coding Challenge Project of PF.
 */
public class PropertyFragment extends Fragment implements PropertyContract.View {

    // to unbind ButterKnife on view destroy
    protected Unbinder mUnbinder = null;

    // view binding
    @BindView(R.id.rv_properties)
    RecyclerView rvProperties;

    @BindView(R.id.tv_no_item_label)
    TextView tvNoItemLabel;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.tv_sort_order)
    TextView tvSortOrder;

    // Presenter of property fragment in MVP
    private PropertyPresenter mPresenter;

    private LinearLayoutManager mLayoutManager;
    private PropertyAdapter mPropertyAdapter;
    private List<PropertyResource> mPropertyList;

    // to hold multiple next page loading calls
    private boolean mIsLoading;

    // User selected sort
    private String mSortOrder;

    public PropertyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_property, container, false);

        // ButterKnife data binding
        mUnbinder = ButterKnife.bind(this, rootView);

        // Initiate presenter right after view setup
        this.mPresenter = new PropertyPresenter(this);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mSortOrder = bundle.getString(Params.ORDER_BEHAVIOURS);
            if (mSortOrder != null && !mSortOrder.isEmpty()) {
                String sortString = "Sorting order: " +
                        (mSortOrder.contains("b")? "Beds" : "Price") + " " +
                        (mSortOrder.contains("a")? "Ascending" : "Descending");

                tvSortOrder.setText(sortString);
                tvSortOrder.setVisibility(View.VISIBLE);
            } else {
                tvSortOrder.setVisibility(View.GONE);
            }
        }

        // First call for list from start of screen
        mPresenter.getPropertyData(0, mSortOrder);
        mIsLoading = false;

        setupRecyclerView();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Unbinding data view
        if (mUnbinder != null)
            mUnbinder.unbind();

        // releasing resources held by presenter
        if (mPresenter != null)
            mPresenter.onRelease();
    }

    private void setupRecyclerView() {

        // setup of recycler view
        mLayoutManager = new LinearLayoutManager(rvProperties.getContext(),
                LinearLayoutManager.VERTICAL, false);
        rvProperties.setLayoutManager(mLayoutManager);

        // initialize list before sending to adapter
        if (mPropertyList == null)
            mPropertyList = new ArrayList<>();
        else
            mPropertyList.clear();

        mPropertyAdapter = new PropertyAdapter(mPropertyList);
        rvProperties.setAdapter(mPropertyAdapter);
        rvProperties.setItemAnimator(new DefaultItemAnimator());

        rvProperties.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int firstVisibleItemIndex, visibleItemCount, totalItemCount;
                    firstVisibleItemIndex = mLayoutManager.findFirstVisibleItemPosition();
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();

                    if (!mIsLoading) {
                        // next page data fetched when last 5 items remaining to show
                        if ((totalItemCount - firstVisibleItemIndex) <= (visibleItemCount * 5)) {
                            mIsLoading = true;
                            //Pagination: fetch new data
                            mPresenter.getPropertyData(totalItemCount, mSortOrder);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void setSearchedData(List<PropertyResource> propertyResourceList) {

        progressBar.setVisibility(View.GONE);

        this.mPropertyList.addAll(propertyResourceList);
        mIsLoading = false;

        if (mPropertyList == null || mPropertyList.size() == 0) {
            tvNoItemLabel.setVisibility(View.VISIBLE);
        } else {
            mPropertyAdapter.appendPropertyList(propertyResourceList);
        }
    }

    @Override
    public void showError(int errorCode) {

        progressBar.setVisibility(View.GONE);
        SnackBarUtil.ShowSnackBarAlert(getContext(), getString(errorCode), null);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
