package com.test.codingchallenge.views.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.test.codingchallenge.R;
import com.test.codingchallenge.util.Params;
import com.test.codingchallenge.views.fragments.PropertyFragment;
import com.test.codingchallenge.views.fragments.SortOptionsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main Screen with default view of search result
 */
public class HomeActivity extends AppCompatActivity {

    // view binding
    @BindView(R.id.home_toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbar_sort)
    TextView tvSort;

    // Data list fragment
    private PropertyFragment mPropertyFragment;
    private SortOptionsFragment mSortOptionsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // calling ButterKnife right after setting content view
        // binding ButterKnife for views
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Property List");
        }

        // Property fragment with default values
        mPropertyFragment = new PropertyFragment();
        addFragment(R.id.frameLayout_home, mPropertyFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSortOptionsFragment != null)
            removeFragment(mSortOptionsFragment);
    }

    @Override
    public void onBackPressed() {

        if (mSortOptionsFragment != null) {
            moveToPropertyScreen("");
            return;
        }
        super.onBackPressed();
    }


    public void OnClickSortView(View view) {

        if (mSortOptionsFragment != null) {
            moveToPropertyScreen("");
            return;
        }

        removeFragment(mPropertyFragment);

        if (mSortOptionsFragment == null) {
            mSortOptionsFragment = new SortOptionsFragment();
            mSortOptionsFragment.setOnOptionSelectionListener(
                    new SortOptionsFragment.OnOptionSelectionListener() {
                        @Override
                        public void onOptionSelected(String sortOption) {
                            moveToPropertyScreen(sortOption);
                        }
                    }
            );
        }
        addFragment(R.id.frameLayout_home, mSortOptionsFragment);

        tvSort.setText(getString(R.string.reset));
    }

    private void moveToPropertyScreen(String sortOption) {

        removeFragment(mSortOptionsFragment);
        mSortOptionsFragment = null;

//        mPropertyFragment = new PropertyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Params.ORDER_BEHAVIOURS, sortOption);
        mPropertyFragment.setArguments(bundle);
        addFragment(R.id.frameLayout_home, mPropertyFragment);
        tvSort.setText(getString(R.string.sort));
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        try {
            final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(containerViewId, fragment);
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * @param fragment The fragment to be removed.
     */
    protected void removeFragment(Fragment fragment) {
        if (fragment != null) {
            final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.remove(fragment);
            try {
                fragmentTransaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    fragmentTransaction.commitAllowingStateLoss();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
