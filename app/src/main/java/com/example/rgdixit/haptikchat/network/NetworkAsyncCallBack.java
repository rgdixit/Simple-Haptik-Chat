package com.example.rgdixit.haptikchat.network;

import android.content.Context;
import android.support.design.widget.Snackbar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by rgdixit on 5/27/16.
 */
public abstract class NetworkAsyncCallBack<T> implements Response.ErrorListener, Response.Listener<JSONObject> {
    private Context mContext;
    private Class mClass;

    protected NetworkAsyncCallBack(Class<T> aClass, Context context) {
        mContext = context.getApplicationContext();
        mClass = aClass;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        onFailure("diagnosed errror");
    }

    @Override
    public void onResponse(JSONObject response) {
        if (response != null) {
            onSuccessResponse((T) new Gson().fromJson(response.toString(), mClass));
        } else {
            onFailure("Ambiguous Error");
        }
    }


    protected abstract void onSuccessResponse(T object);

    protected abstract void onFailure(String message);
}
