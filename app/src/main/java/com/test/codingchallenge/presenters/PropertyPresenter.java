package com.test.codingchallenge.presenters;

import com.test.codingchallenge.R;
import com.test.codingchallenge.interactors.PropertyInteractor;
import com.test.codingchallenge.contracts.PropertyContract;
import com.test.codingchallenge.models.search.PropertyResource;
import com.test.codingchallenge.util.ErrorCodes;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Presenter Class property screen for MVP.
 */
public class PropertyPresenter implements PropertyContract.Presenter {

    private WeakReference<PropertyContract.View> mView;
    private final PropertyInteractor mInteractor;

    private int mPageNumber;
    private int mPageSize;
    private int mTotalItemSize;
    private int mExpectedPageSize;

    public PropertyPresenter(PropertyContract.View view) {
        this.mView = new WeakReference<>(view);
        this.mInteractor = new PropertyInteractor();

        mPageNumber = 1; // initial or default value of page
    }

    public void onRelease() {
        this.mView = new WeakReference<>(null);
        this.mInteractor.onRelease();
    }

    @Override
    public void getPropertyData(final int lastItemIndex, String sortOrder) {

        mInteractor.fetchPropertySearchData(mPageNumber++, sortOrder,
                new PropertyContract.Interactor.OnCompleteSearchedDataListener() {
                    @Override
                    public void onSuccess(List<PropertyResource> propertyResourceList, Integer totalItems) {

                        // dynamically determining page size from first call
                        if (lastItemIndex == 0 && propertyResourceList != null) {
                            mPageSize = propertyResourceList.size();
                            if (totalItems != null)
                                mTotalItemSize = totalItems;
                            mExpectedPageSize = mTotalItemSize / mPageSize +
                                    (mTotalItemSize % mPageSize > 0 ? 1 : 0);
                        }

                        // send data to view
                        if (mView.get() != null)
                            mView.get().setSearchedData(propertyResourceList);
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
