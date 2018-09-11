package com.facerec.tasol.androiarchitecturecomponent.model_services.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.facerec.tasol.androiarchitecturecomponent.model_services.model.MapModel;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by tasol on 10/7/18.
 */

@Dao
public interface MapModelDao {
    @Query("select * from MapModel")
    LiveData<List<MapModel>> getAllMapData();

    @Query("select * from MapModel where mapPlaceId = :MAPPLACEID")
    MapModel getMapDataDetail(String MAPPLACEID);

    @Insert(onConflict = REPLACE)
    void insertMapData(MapModel model);

    @Delete
    void deleteMapData(MapModel model);

}
