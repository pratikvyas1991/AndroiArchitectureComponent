package com.facerec.tasol.androiarchitecturecomponent.model_services.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by tasol on 10/7/18.
 */

@Entity
public class MapModel {
    @PrimaryKey(autoGenerate = true)
    public int mapPlaceId;
    private String mapPlaceName;
    private double mapLatitude;
    private double mapLongitude;

    public MapModel(String mapPlaceName, double mapLatitude, double mapLongitude) {
        this.mapPlaceName = mapPlaceName;
        this.mapLatitude = mapLatitude;
        this.mapLongitude = mapLongitude;
    }

    public int getMapPlaceId() {
        return mapPlaceId;
    }

    public String getMapPlaceName() {
        return mapPlaceName;
    }

    public double getMapLatitude() {
        return mapLatitude;
    }

    public double getMapLongitude() {
        return mapLongitude;
    }
}
