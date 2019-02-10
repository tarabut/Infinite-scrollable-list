package com.test.codingchallenge.interactors;

import android.support.annotation.NonNull;

import com.test.codingchallenge.contracts.HomeContract;
import com.test.codingchallenge.data.source.rest.ApiClient;
import com.test.codingchallenge.data.source.rest.ApiInterface;
import com.test.codingchallenge.models.search.SearchListResponse;
import com.test.codingchallenge.util.ErrorCodes;
import com.test.codingchallenge.util.Params;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Web services interactor home screen for MVP.
 */
public class HomeInteractor implements HomeContract.Interactor {

    // Instance to fetch data from APIs via Retrofit client
    private ApiInterface mApiInteractor;

    public HomeInteractor() {
        mApiInteractor = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void fetchSearchData(int pageNumber, final HomeContract.OnCompleteSearchedDataListener listener) {

        Map<String, String> options = new HashMap<>();
        options.put(Params.PAGE, String.valueOf(pageNumber));

        Call<SearchListResponse> apiCall = mApiInteractor.getSearchedData(options);
        apiCall.enqueue(new Callback<SearchListResponse>() {
            @Override
            public void onResponse(@NonNull Call<SearchListResponse> call, @NonNull Response<SearchListResponse> response) {

                if (response.code() == 200) {
                    SearchListResponse body = response.body();
                    if (body != null && body.getSearchResource() != null) {
                        if (listener != null)
                            listener.onSuccess(body.getSearchResource());
                    } else {
                        if (listener != null)
                            listener.onFailure(ErrorCodes.ERROR_DATA_FAILED_RESPONSE);
                    }
                }
                if (listener != null)
                    listener.onFailure(ErrorCodes.ERROR_API_FAILED_RESPONSE);
            }

            @Override
            public void onFailure(@NonNull Call<SearchListResponse> call, @NonNull Throwable t) {
                if (listener != null)
                    listener.onFailure(ErrorCodes.ERROR_CONNECTIVITY);
            }
        });
    }
}
