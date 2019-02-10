package com.test.codingchallenge.interactors;

import android.support.annotation.NonNull;

import com.test.codingchallenge.contracts.PropertyContract;
import com.test.codingchallenge.data.source.rest.ApiClient;
import com.test.codingchallenge.data.source.rest.ApiInterface;
import com.test.codingchallenge.models.search.PropertyListResponse;
import com.test.codingchallenge.util.ErrorCodes;
import com.test.codingchallenge.util.Params;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Web services interactor home screen for MVP.
 */
public class PropertyInteractor implements PropertyContract.Interactor {

    // Instance to fetch data from APIs via Retrofit client
    private ApiInterface mApiInteractor;

    private List<Call<PropertyListResponse>> mApiCallList;

    public PropertyInteractor() {
        mApiInteractor = ApiClient.getClient().create(ApiInterface.class);
    }

    public void onRelease() {

        // just making sure to cancel remaining pending API calls
        if (mApiCallList != null) {
            for (Call<PropertyListResponse> apiCall : mApiCallList) {
                if (apiCall != null && !apiCall.isCanceled())
                    apiCall.cancel();
            }
        }
    }

    @Override
    public void fetchPropertySearchData(int pageNumber, String orderSort,
                                        final OnCompleteSearchedDataListener listener) {

        Map<String, String> options = new HashMap<>();
        options.put(Params.PAGE, String.valueOf(pageNumber));

        // only adding this parameter in case sorting is selected
        if (orderSort != null && orderSort.isEmpty())
            options.put(Params.ORDER_BEHAVIOURS, orderSort);

        Call<PropertyListResponse> apiCallSearchedData = mApiInteractor.getPropertySearchedData(options);
        apiCallSearchedData.enqueue(new Callback<PropertyListResponse>() {
            @Override
            public void onResponse(@NonNull Call<PropertyListResponse> call, @NonNull Response<PropertyListResponse> response) {

                // First checking API executed successfully
                if (response.code() == 200) {
                    // validate data received from api
                    PropertyListResponse body = response.body();
                    if (body != null && body.getPropertyResource() != null) {
                        // All good. Let's forward data
                        if (listener != null)
                            listener.onSuccess(body.getPropertyResource(), body.getTotal());
                    } else {
                        // Oh no! Some thing went wrong in successful API
                        // API validation required at this point
                        // Meanwhile show error to user
                        if (listener != null)
                            listener.onFailure(ErrorCodes.ERROR_DATA_FAILED_RESPONSE);
                    }
                } else {
                    // Oops! API failed with some error like 500, 415 etc
                    if (listener != null)
                        listener.onFailure(ErrorCodes.ERROR_API_FAILED_RESPONSE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PropertyListResponse> call, @NonNull Throwable t) {
                // handling connectivity errors
                // Reasons to land here:
                // Connectivity to internet or API server
                if (listener != null)
                    listener.onFailure(ErrorCodes.ERROR_CONNECTIVITY);
            }
        });

        if (mApiCallList == null) {
            // optimization step:
            // Memory - In case of none necessary API call initiating list at the moment of first API call
            mApiCallList = new ArrayList<>();
        }
        mApiCallList.add(apiCallSearchedData);
    }
}
