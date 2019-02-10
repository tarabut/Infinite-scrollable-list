package com.test.codingchallenge.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created for AER Android App by Infiniun Healthcare.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    private NetworkChangeListener mNetworkChangeListener = null;

    public NetworkChangeReceiver(NetworkChangeListener networkChangeListener) {
        this.mNetworkChangeListener = networkChangeListener;
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {

        if (mNetworkChangeListener != null)
            mNetworkChangeListener.onNetworkChange(checkInternet(context));
    }

    boolean checkInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }

    public interface NetworkChangeListener {
        void onNetworkChange(boolean isNetworkAvailable);
    }
}