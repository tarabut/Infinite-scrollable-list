package com.test.codingchallenge.data;

/**
 * Data Manager for whole app
 */
public class DataManager {

    private SharedPrefsHelper mSharedPrefsHelper;

    private static DataManager mDataManagerInstance;

    private DataManager () {
        mSharedPrefsHelper = null;
    }

    public void setInstance(SharedPrefsHelper sharedPrefsHelper) {
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void release() {
        if (mDataManagerInstance != null)
            mDataManagerInstance = null;
    }

    public static DataManager getInstance() {
        if (mDataManagerInstance == null)
            mDataManagerInstance = new DataManager();
        return mDataManagerInstance;
    }

    public String getFcmToken() {
        return mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_FCM_TOKEN, "");
    }

    public void setFcmToken(String fcmToken) {
        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_FCM_TOKEN, fcmToken);
    }

    public String getPrefValue(String keyValue) {
        return mSharedPrefsHelper.get(keyValue,"");
    }

    public void setPrefValue(String keyValue, String prefValue){
        mSharedPrefsHelper.put(keyValue, prefValue);
    }
}
