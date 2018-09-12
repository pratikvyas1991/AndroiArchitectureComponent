package com.facerec.tasol.androiarchitecturecomponent.model_services.networking;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by tasol on 8/9/18.
 */

public class Networking {
    private static RequestQueue mRequestQueue;
    private static final String TAGWS = "%%%Networking ";

    public static void getMapData(Context mContext) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, NetworkingConstant.mBaseUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d(TAGWS,"Response :"+ response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAGWS, "Error " + error.toString());
                    }
                }
        );
        Log.d(TAGWS, "ReqObj : "+getRequest.toString());
        mRequestQueue.add(getRequest);
        mRequestQueue = null;
    }

}
