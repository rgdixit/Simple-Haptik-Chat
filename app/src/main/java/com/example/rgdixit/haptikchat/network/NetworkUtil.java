package com.example.rgdixit.haptikchat.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

/**
 * Created by rgdixit on 5/27/16.
 */
public class NetworkUtil {


    private static final String TAG = "Network Util";

    public static void doAsyncGetCall(Context context, String url, NetworkAsyncCallBack cb) {
        Log.d(TAG, url);
        VolleySingleton volley = VolleySingleton.getInstance(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, "", cb, cb);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10 * 1000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        volley.getQueue().add(jsonObjectRequest);
    }
}
