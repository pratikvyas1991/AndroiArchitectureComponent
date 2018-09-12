package com.facerec.tasol.androiarchitecturecomponent.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facerec.tasol.androiarchitecturecomponent.model_services.database.StudentDatabase;
import com.facerec.tasol.androiarchitecturecomponent.model_services.model.MapModel;
import com.facerec.tasol.androiarchitecturecomponent.model_services.networking.NetworkingConstant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tasol on 10/7/18.
 */

public class MapViewModel extends AndroidViewModel {
    private StudentDatabase mStudentDatabase;
    private static RequestQueue mRequestQueue;
    private static final String TAGMAPVM = "%%##MapViewModel";
    private List<MapModel> mapList =new ArrayList<>();
    private MutableLiveData<List<MapModel>> mMapLiveDataList;

    public MapViewModel(Application application) {
        super(application);
        mStudentDatabase = StudentDatabase.getDatabase(this.getApplication());
    }

    public LiveData<List<MapModel>> getMapDataListList() {
        // get Map Data  List
        return mStudentDatabase.mapModelDao().getAllMapData();
    }


    public LiveData<List<MapModel>> getMapDataListResponse(Context context) {
        // get Map Data  List
        if (mMapLiveDataList == null) {
            mMapLiveDataList = new MutableLiveData<List<MapModel>>();
            //we will load it asynchronously from server in this method
            getMapDataResponse(context);
        }
        return mMapLiveDataList;
    }

    public void getMapDataResponse(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        try {
            JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, NetworkingConstant.mBaseUrl, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // display response
                            if(response!=null){
                                try {
                                    if(response.has("results")){
                                        JSONArray results = response.getJSONArray("results");
                                        mapList.clear();
                                        for (int i = 0; i < results.length(); i++) {
                                            JSONObject row = results.getJSONObject(i);
                                            String placeName = row.getString("name");
                                            double placeLatitude = 0;
                                            double placeLongitude = 0;
                                            if(row.has("geometry")){
                                                JSONObject mGeometry = row.getJSONObject("geometry");
                                                if(mGeometry.has("location")){
                                                    JSONObject locat = mGeometry.getJSONObject("location");
                                                    if(locat.has("lat")){
                                                        placeLatitude = locat.getDouble("lat");
                                                    }
                                                    if(locat.has("lng")){
                                                        placeLongitude = locat.getDouble("lng");
                                                    }
                                                }
                                            }
                                            mapList.add(new MapModel(placeName,placeLatitude,placeLongitude));
                                        }
                                        if(mapList!=null && mapList.size()>0){
                                            mMapLiveDataList.setValue(mapList);
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
//                            Log.d(TAGMAPVM,"Response :"+ response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAGMAPVM, "Error " + error.toString());
                        }
                    }
            );
            Log.d(TAGMAPVM, "ReqObj : "+getRequest.toString());
            mRequestQueue.add(getRequest);
            mRequestQueue = null;
        }catch (Exception je){
            je.printStackTrace();
        }
    }

    public void addMapData(MapModel model) {
        // Add Map Data Here
        new MapViewModel.addMapDataTask(mStudentDatabase).equals(model);
    }

    private class addMapDataTask extends AsyncTask<MapModel, Void, Void> {
        StudentDatabase mStudentDatabase;

        public addMapDataTask(StudentDatabase mStudentDatabase) {
            this.mStudentDatabase = mStudentDatabase;
        }

        @Override
        protected Void doInBackground(MapModel... mapModels) {
            mStudentDatabase.mapModelDao().insertMapData(mapModels[0]);
            return null;
        }
    }

}
