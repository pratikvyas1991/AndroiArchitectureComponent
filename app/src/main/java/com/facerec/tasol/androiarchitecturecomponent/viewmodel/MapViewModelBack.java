package com.facerec.tasol.androiarchitecturecomponent.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.facerec.tasol.androiarchitecturecomponent.model_services.database.StudentDatabase;
import com.facerec.tasol.androiarchitecturecomponent.model_services.model.MapModel;
import com.facerec.tasol.androiarchitecturecomponent.model_services.networking.Networking;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by tasol on 10/7/18.
 */

public class MapViewModelBack extends AndroidViewModel {
    private StudentDatabase mStudentDatabase;
    private static final String TAGMAPVM = "%%##MapViewModel";

    public MapViewModelBack(Application application) {
        super(application);
        mStudentDatabase = StudentDatabase.getDatabase(this.getApplication());
    }

    public LiveData<List<MapModel>> getMapDataListList() {
        // get Map Data  List
        return mStudentDatabase.mapModelDao().getAllMapData();
    }

    public void getMapData(Context context) {
        Networking.getMapData(context);
    }

    public void addMapData(MapModel model) {
        // Add Map Data Here
        new MapViewModelBack.addMapDataTask(mStudentDatabase).equals(model);
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
