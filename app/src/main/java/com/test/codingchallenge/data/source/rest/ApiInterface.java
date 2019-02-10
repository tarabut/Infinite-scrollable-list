package com.test.codingchallenge.data.source.rest;

import com.test.codingchallenge.models.search.PropertyListResponse;
import com.test.codingchallenge.util.Urls;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

/**
 * Retrofit interface for web services
 */
public interface ApiInterface {

    @GET(Urls.GET_PROPERTY_SEARCHED_DATA)
    @Headers({"Content-Type: application/json"})
    Call<PropertyListResponse> getPropertySearchedData(@QueryMap Map<String, String> options);

}
