package com.test.codingchallenge.data.source.rest;

import com.test.codingchallenge.BuildConfig;
import com.test.codingchallenge.util.Constants;
import com.test.codingchallenge.util.Urls;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit client.
 */
public class ApiClient {

    private static Retrofit retrofit = null;
 
 
    public static Retrofit getClient() {
        if (retrofit==null) {
            String baseUrl = Urls.BASE_URL_STAGING;
            if (BuildConfig.BUILD_TYPE.equalsIgnoreCase(Constants.BUILD_VAR_RELEASE)) {
                baseUrl = Urls.BASE_URL;
            }

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}