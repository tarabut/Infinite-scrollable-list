package com.test.codingchallenge.contracts;

import com.test.codingchallenge.models.search.PropertyResource;

import java.util.List;

/**
 * Contract home screen for MVP.
 */
public interface PropertyContract {

    interface View {

        void setSearchedData(List<PropertyResource> propertyResourceList);

        void showError(int errorCodeId);

        void showProgress ();
    }

    interface Presenter {

        void getPropertyData(int lastItemIndex, String sortOrder);
    }

    interface Interactor {

        void fetchPropertySearchData(int pageNumber, String orderSort, OnCompleteSearchedDataListener listener);

        interface OnCompleteSearchedDataListener {

            void onSuccess(List<PropertyResource> propertyResourceList, Integer totalItems);

            void onFailure (int errorCode);
        }
    }
}
