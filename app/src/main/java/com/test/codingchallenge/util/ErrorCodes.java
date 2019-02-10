package com.test.codingchallenge.util;

/**
 * Error Codes used through out app
 */
public class ErrorCodes {

    // to identify connectivity error
    public static final int ERROR_CONNECTIVITY          = 5000;

    // in case of API failure: error code 500
    public static final int ERROR_API_FAILED_RESPONSE   = 5001;

    // in case of Data retrieval failed via API
    public static final int ERROR_DATA_FAILED_RESPONSE  = 5002;

    // to handle generic error
    public static final int ERROR_GENERIC_RESPONSE      = 5003;
}
