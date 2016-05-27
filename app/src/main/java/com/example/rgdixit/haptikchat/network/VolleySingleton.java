package com.example.rgdixit.haptikchat.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by rgdixit on 5/27/16.
 */
public final class VolleySingleton {

    private static VolleySingleton INSTANCE = null;
    private RequestQueue mRequestQueue;

    private VolleySingleton(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new VolleySingleton(context);
        }
        return INSTANCE;
    }


    public RequestQueue getQueue() {
        return mRequestQueue;
    }


}
