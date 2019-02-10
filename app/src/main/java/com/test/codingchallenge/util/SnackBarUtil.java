package com.test.codingchallenge.util;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.codingchallenge.R;

public class SnackBarUtil {

    public static void showSnackBarAlert(Context context, String message) {

        showSnackBarAlert(context.getString(R.string.text_ok), context, message,
                Snackbar.LENGTH_LONG, null, ContextCompat.getColor(context, R.color.colorError));
    }

    public static void showSnackBarAlert(Context context, String message,
                                         final OnActionSelectedListener onActionSelectedListener) {
        if (context == null) {
            return;
        }

        showSnackBarAlert(context.getString(R.string.text_ok), context, message,
                Snackbar.LENGTH_LONG, onActionSelectedListener, ContextCompat.getColor(context, R.color.colorError));
    }

    public static void showSnackBarAlert(String positionButtonText, Context context, String message,
                                         final OnActionSelectedListener onActionSelectedListener) {

        showSnackBarAlert(positionButtonText, context, message,Snackbar.LENGTH_LONG,
                onActionSelectedListener, ContextCompat.getColor(context, R.color.colorError));
    }

    public static Snackbar showSnackBarAlert(String positionButtonText, Context context, String message,
                                             int length, final OnActionSelectedListener onActionSelectedListener, int backgroundColor) {
        if (context == null) {
            return null;
        }

        if (backgroundColor == 0)
            backgroundColor = ContextCompat.getColor(context, R.color.colorError);

        final ViewGroup rootView = (ViewGroup) ((ViewGroup) ((Activity) context)
                .findViewById(android.R.id.content)).getChildAt(0);

        final Snackbar snackbar = Snackbar.make(rootView, message, length);

        snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.colorTextError));
        snackbar.getView().setBackgroundColor(backgroundColor);
        snackbar.setAction(positionButtonText, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onActionSelectedListener != null) {
                    onActionSelectedListener.onActionSelected();
                }
                snackbar.dismiss();
            }
        });

        if (length != Snackbar.LENGTH_INDEFINITE) {
            snackbar.setDuration(6000);
        }

        //Get reference of snackbar textview
        TextView textView = snackbar.getView()
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setMaxLines(10);
        snackbar.show();

        return snackbar;
    }

    public interface OnActionSelectedListener {
        void onActionSelected();
    }
}
