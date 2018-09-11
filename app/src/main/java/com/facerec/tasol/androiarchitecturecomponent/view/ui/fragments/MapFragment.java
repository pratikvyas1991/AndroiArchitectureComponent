package com.facerec.tasol.androiarchitecturecomponent.view.ui.fragments;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facerec.tasol.androiarchitecturecomponent.R;
import com.facerec.tasol.androiarchitecturecomponent.viewmodel.MapViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by tasol on 5/9/18.
 */

public class MapFragment extends MasterFragment implements OnMapReadyCallback {
    private MapView mMapView;
    private GoogleMap mMap;
    private MapViewModel mapViewModel;
    public MapFragment() {
    }
    @Override
    public View fragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, parent, false);
        mapViewModel = ViewModelProviders.of(getActivity()).get(MapViewModel.class);
        MapsInitializer.initialize(getActivity());
        mMapView = (MapView)view.findViewById(R.id.map_view);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
//        mapViewModel.getMapData(getActivity());
        mapViewModel.getMapDataResponse(getActivity());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addMarkerData();
    }

    @SuppressLint("MissingPermission")
    private void addMarkerData(){
        LatLng ahmedabad = new LatLng(23.0382813,72.5100283);
//        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ahmedabad,13));
        mMap.addMarker(new MarkerOptions()
                .title(getString(R.string.marker_title))
                .position(ahmedabad)
        );
    }
}
