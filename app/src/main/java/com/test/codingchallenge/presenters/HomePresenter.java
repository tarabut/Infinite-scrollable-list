package com.test.codingchallenge.presenters;

import com.test.codingchallenge.R;
import com.test.codingchallenge.interactors.HomeInteractor;
import com.test.codingchallenge.contracts.HomeContract;
import com.test.codingchallenge.models.search.SearchResource;
import com.test.codingchallenge.util.ErrorCodes;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Presenter Class home screen for MVP.
 */
public class HomePresenter implements HomeContract.Presenter {

    private WeakReference<HomeContract.View> mView;
    private final HomeInteractor mInteractor;

    private int mPageNumber;

    public HomePresenter(HomeContract.View view) {
        this.mView = new WeakReference<>(view);
        this.mInteractor = new HomeInteractor();

        mPageNumber = 1; // initial or default value of page
    }

    public void onRelease() {
        this.mView = new WeakReference<>(null);
    }

    @Override
    public void getSearchData() {

        mInteractor.fetchSearchData(mPageNumber++,
                new HomeContract.OnCompleteSearchedDataListener() {
                    @Override
                    public void onSuccess(List<SearchResource> searchResourceList) {
                        if (mView.get() != null)
                            mView.get().setSearchedData(searchResourceList);
                    }

                    @Override
                    public void onFailure(int errorCode) {

                        //parsing error on bases of error code
                        if (mView.get() != null) {
                            int stringId;
                            switch (errorCode) {
                                case ErrorCodes.ERROR_API_FAILED_RESPONSE:
                                    stringId = R.string.error_msg_api_faiure;
                                    break;
                                case ErrorCodes.ERROR_DATA_FAILED_RESPONSE:
                                    stringId = R.string.error_msg_data_faiure;
                                    break;
                                case ErrorCodes.ERROR_CONNECTIVITY:
                                    stringId = R.string.error_msg_connectivity;
                                    break;
                                    default:
                                        stringId = R.string.error_msg_generic;
                                        break;
                            }

                            mView.get().showError(stringId);
                        }
                    }
                });
    }
}
