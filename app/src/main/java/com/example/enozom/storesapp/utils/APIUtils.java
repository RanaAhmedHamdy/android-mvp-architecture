package com.example.enozom.storesapp.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by enozom on 9/11/2017.
 */

public class APIUtils {
    private RequestQueue mRequestQueue;
    private final static String TAG = "APIUtils";
    private static APIUtils sInstance;
    private Context ctx;
    public static final int GET_METHOD =  Request.Method.GET;

    private APIUtils(Context ctx) {
        this.ctx = ctx;
    }

    public static synchronized APIUtils getInstance(@NonNull Context ctx) {
        if (sInstance == null) {
            sInstance = new APIUtils(ctx);
        }
        return sInstance;
    }

    public void requestJsonArray(@NonNull int method, @NonNull String strUrl, @Nullable HashMap<String, String> params, @NonNull final boolean useApiKey, final VolleyCallbackArray callback) {
        try {
            JSONObject jsonRequest = null;
            if (params != null) {
                jsonRequest = new JSONObject(params);
            }

            final JsonArrayRequest req = new CustomJsonArrayRequest(method, strUrl, new JSONArray().put(jsonRequest),
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, "requestJsonArray:response " + response.toString());
                            callback.onSuccess(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            NetworkResponse response = error.networkResponse;
                            if (error != null) {
                                callback.onConnectionError();
                                Log.e(TAG, "requestJsonArray:responseon:ErrorResponse:volleyError " + error.getMessage());
                            } else if (response != null && response.data != null) {
                                switch (response.statusCode) {
                                    case 500:
                                        callback.onServerError();
                                        break;
                                    default:
                                        callback.onConnectionError();
                                        break;
                                }
                                Log.e(TAG, "requestJsonArray:response.statusCode " + response.statusCode);
                            } else {
                                callback.onConnectionError();
                            }
                        }
                    })

            {

            };
            addToRequestQueue(req);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                Log.e(TAG, "requestJsonArray:Exception " + e.getMessage());
            }
        }
    }

    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);
        req.setRetryPolicy(new DefaultRetryPolicy(
                6000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        getRequestQueue().add(req);
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(ctx);
        }

        return mRequestQueue;
    }


    public interface VolleyCallbackArray {
        void onSuccess(@Nullable JSONArray result);

        void onServerError();

        void onConnectionError();

        void onCustomError(@NonNull String message);
    }

    public class CustomJsonArrayRequest extends JsonArrayRequest {

        public CustomJsonArrayRequest(int method, String url, JSONArray jsonRequest, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
            super(method, url, jsonRequest, listener, errorListener);
        }

        @Override
        protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
            try {
                String json = new String(response.data, "UTF-8");

                if (json.length() == 0) {
                    return Response.success(null, HttpHeaderParser.parseCacheHeaders(response));
                } else {
                    return super.parseNetworkResponse(response);
                }
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            }
        }
    }
}
