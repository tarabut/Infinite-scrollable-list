package com.test.codingchallenge.views.activities;

import android.content.IntentFilter;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.test.codingchallenge.R;
import com.test.codingchallenge.receivers.NetworkChangeReceiver;
import com.test.codingchallenge.util.Params;
import com.test.codingchallenge.util.SnackBarUtil;
import com.test.codingchallenge.views.fragments.PropertyFragment;
import com.test.codingchallenge.views.fragments.SortOptionsFragment;

//import butterknife.BindView;
//import butterknife.ButterKnife;

/**
 * Main Screen with default view of search result
 */
public class HomeActivity extends AppCompatActivity {

    // view binding
//    @BindView(R.id.home_toolbar)
    Toolbar toolbar;
//    @BindView(R.id.tv_toolbar_sort)
    TextView tvSort;

    // Data list fragment
    private PropertyFragment mPropertyFragment;
    private SortOptionsFragment mSortOptionsFragment;

    //to track internet connectivity
    private NetworkChangeReceiver mNetworkChangeReceiver;
    private Snackbar mSnakeBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        // calling ButterKnife right after setting content view
//        // binding ButterKnife for views
//        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Property List");
        }

        // Property fragment with default values
        mPropertyFragment = new PropertyFragment();
        addFragment(R.id.frameLayout_home, mPropertyFragment);

        if (mNetworkChangeReceiver == null) {
            mNetworkChangeReceiver = new NetworkChangeReceiver(new NetworkChangeReceiver.NetworkChangeListener() {
                @Override
                public void onNetworkChange(final boolean isNetworkAvailable) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!isNetworkAvailable) {
                                mSnakeBar = SnackBarUtil.showSnackBarAlert("Ok",
                                        HomeActivity.this,
                                        getString(R.string.error_msg_connectivity),
                                        Snackbar.LENGTH_LONG, null, 0);
                            } else {
                                if (mSnakeBar != null)
                                    mSnakeBar.dismiss();
                            }
                        }
                    });
                }
            });
            IntentFilter filter = new IntentFilter();
            filter.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(mNetworkChangeReceiver, filter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSortOptionsFragment != null)
            removeFragment(mSortOptionsFragment);

        if (mNetworkChangeReceiver != null)
            unregisterReceiver(mNetworkChangeReceiver);
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
