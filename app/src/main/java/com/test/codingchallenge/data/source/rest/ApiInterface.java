package com.test.codingchallenge.data.source.rest;

import com.test.codingchallenge.models.search.SearchListResponse;
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

    @GET(Urls.GET_SEARCHED_DATA)
    @Headers({"Content-Type: application/json"})
    Call<SearchListResponse> getSearchedData(@QueryMap Map<String, String> options);

}
