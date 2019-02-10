package com.test.codingchallenge.contracts;

import com.test.codingchallenge.models.search.SearchResource;

import java.util.List;

/**
 * Contract home screen for MVP.
 */
public interface HomeContract {

    public interface View {

        void setSearchedData(List<SearchResource> searchResourceList);

        void showError(int errorCodeId);

        void showProgress ();
    }

    public interface Presenter {

        void getSearchData();
    }

    public interface Interactor {

        void fetchSearchData(int pageNumber, OnCompleteSearchedDataListener listener);
    }

    public interface OnCompleteSearchedDataListener {

        void onSuccess (List<SearchResource> searchResourceList);

        void onFailure (int errorCode);
    }
}
