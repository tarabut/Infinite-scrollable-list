package com.test.codingchallenge.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.codingchallenge.R;
import com.test.codingchallenge.contracts.HomeContract;
import com.test.codingchallenge.models.search.SearchResource;
import com.test.codingchallenge.presenters.HomePresenter;
import com.test.codingchallenge.util.SnackBarUtil;

import java.util.List;

/**
 * Main Screen with default view of search result
 */
public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    // Presenter of Home activity in MVP
    private HomePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.mPresenter = new HomePresenter(this);

        mPresenter.getSearchData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mPresenter != null)
            mPresenter.onRelease();
    }

    @Override
    public void setSearchedData(List<SearchResource> searchResourceList) {

    }

    @Override
    public void showError(int errorCode) {
//        todo dismiss progress bar if any
        SnackBarUtil.ShowSnackBarAlert(HomeActivity.this, getString(errorCode), null);
    }

    @Override
    public void showProgress() {

    }
}
